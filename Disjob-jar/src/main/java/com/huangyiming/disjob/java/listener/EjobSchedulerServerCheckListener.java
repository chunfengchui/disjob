package com.huangyiming.disjob.java.listener;

import java.util.List;

import org.apache.curator.framework.CuratorFramework;

import com.huangyiming.disjob.event.ObjectEvent;
import com.huangyiming.disjob.event.ObjectListener;
import com.huangyiming.disjob.java.CuratorClientBuilder;
import com.huangyiming.disjob.java.EjobConstants;
import com.huangyiming.disjob.java.bean.StartUpConfig;
import com.huangyiming.disjob.java.service.EjobConfigService;
import com.huangyiming.disjob.java.service.StartUpService;
import com.huangyiming.disjob.quence.Log;

/**
 * 检测 当前注册的 ejob 节点是否有无 调度者.在：${zkrootnode}/scheduler/slave
 * @author Disjob
 *
 */
public class EjobSchedulerServerCheckListener implements ObjectListener<StartUpConfig>{

	public void onEvent(ObjectEvent<StartUpConfig> event) {
		try {
			boolean isDiscovery = false ;
			
			CuratorFramework zkClient = CuratorClientBuilder.getInstance().getCuratorFramework();
			boolean isExists = true ;
			if(null == zkClient.checkExists().forPath(EjobConstants.ZKNode.SCHEDULER_SLAVE)){
				Log.warn("below of the "+EjobConfigService.getZKRootNode()+" node, none of the scheduler servers discovery.");
				System.out.println("[EJOB WARNNING] below of the "+EjobConfigService.getZKRootNode()+" node, none of the scheduler servers discovery.");
				isExists = false ;
			}
			
			if(isExists){
				List<String> ips = zkClient.getChildren().forPath(EjobConstants.ZKNode.SCHEDULER_SLAVE);
				StringBuffer schedulerIps = new StringBuffer();
				if(ips!=null&&ips.size()>0){
					for(String ip : ips){
						String slaveIpStatus = String.format(EjobConstants.ZKNode.SCHEDULER_SLAVE_IP_STATUS, ip);
						if(null == zkClient.checkExists().forPath(slaveIpStatus)){
							continue;
						}
						String data = new String(zkClient.getData().forPath(slaveIpStatus),"utf-8");
						if(!EjobConstants.ZKNode.SLAVE_STATUS_READY.equals(data)){
							continue;
						}
						schedulerIps.append(ip);
						schedulerIps.append(", ");
						isDiscovery = true ;
					}
				}
				
				if(isDiscovery){
					Log.info("discovery scheduler servers :"+schedulerIps.toString());
					System.out.println("discovery scheduler servers :"+schedulerIps.toString());
					schedulerIps.setLength(0);
				}
			}
			
			StartUpService.isInitSuccess = isDiscovery;
			if(!isDiscovery){
				Log.warn("below of the "+EjobConfigService.getZKRootNode()+" node, none of the scheduler servers discovery.");
				System.out.println("[EJOB WARNNING] below of the "+EjobConfigService.getZKRootNode()+" node on zookeeper, none of the scheduler servers discovery.");
				throw new RuntimeException("none of the scheduler server discovery on zookeeper .please check the [zkrootnode="+EjobConfigService.getZKRootNode()+"] property in config of xml file or property file is wright ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			StartUpService.isInitSuccess = false ;
			Log.error(e.getMessage(), e);
		}
	}

}
