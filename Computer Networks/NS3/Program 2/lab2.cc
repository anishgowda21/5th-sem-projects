#include "ns3/core-module.h"
#include "ns3/network-module.h"
#include "ns3/internet-module.h"
#include "ns3/point-to-point-module.h"
#include "ns3/applications-module.h"
#include "ns3/traffic-control-module.h"
#include "ns3/flow-monitor-module.h"

using namespace ns3;

int
main (int argc, char *argv[])
{
  double simulationTime = 10; //seconds
  std::string transportProt = "Tcp";
  std::string socketType = "ns3::UdpSocketFactory";

  NodeContainer nodes;
  nodes.Create (4);

  PointToPointHelper pointToPoint;
  pointToPoint.SetDeviceAttribute ("DataRate", StringValue ("10Mbps"));
  pointToPoint.SetChannelAttribute ("Delay", StringValue ("2ms"));
  pointToPoint.SetQueue ("ns3::DropTailQueue", "MaxSize", StringValue ("1p"));

  NetDeviceContainer devices02;
  devices02 = pointToPoint.Install (nodes.Get(0),nodes.Get(2));
  
  NetDeviceContainer devices12;
  devices12 = pointToPoint.Install (nodes.Get(1),nodes.Get(2));
  
  NetDeviceContainer devices23;
  devices23 = pointToPoint.Install (nodes.Get(2),nodes.Get(3));

  InternetStackHelper stack;
  stack.Install (nodes);

  Ipv4AddressHelper address;
  address.SetBase ("10.1.1.0", "255.255.255.0");

  Ipv4InterfaceContainer interfaces02 = address.Assign (devices02);
  
  address.SetBase ("10.1.2.0", "255.255.255.0");

  Ipv4InterfaceContainer interfaces12 = address.Assign (devices12);
  
  address.SetBase ("10.1.3.0", "255.255.255.0");

  Ipv4InterfaceContainer interfaces23 = address.Assign (devices23);

  Ipv4GlobalRoutingHelper::PopulateRoutingTables();
  
  //UdpFlow
  uint16_t port = 7;
  Address localAddress (InetSocketAddress (Ipv4Address::GetAny (), port));
  PacketSinkHelper packetSinkHelper (socketType, localAddress);
  ApplicationContainer sinkApp = packetSinkHelper.Install (nodes.Get (3));

  sinkApp.Start (Seconds (0.0));
  sinkApp.Stop (Seconds (simulationTime + 0.1));


  OnOffHelper onoff (socketType, Ipv4Address::GetAny ());
  onoff.SetAttribute ("OnTime",  StringValue ("ns3::ConstantRandomVariable[Constant=1]"));
  onoff.SetAttribute ("OffTime", StringValue ("ns3::ConstantRandomVariable[Constant=0]"));
  onoff.SetAttribute ("DataRate", StringValue ("50Mbps")); //bit/s
  ApplicationContainer apps;

  InetSocketAddress rmt (interfaces23.GetAddress (1), port);
  rmt.SetTos (0xb8);
  AddressValue remoteAddress (rmt);
  onoff.SetAttribute ("Remote", remoteAddress);
  apps.Add (onoff.Install (nodes.Get (1)));
  apps.Start (Seconds (1.0));
  apps.Stop (Seconds (simulationTime + 0.1));
  
  //TcpFlow
  socketType = "ns3::TcpSocketFactory";
  uint16_t port_tcp = 9;
  Address localAddress_tcp (InetSocketAddress (Ipv4Address::GetAny (), port_tcp));
  PacketSinkHelper packetSinkHelper_tcp (socketType, localAddress_tcp);
  ApplicationContainer sinkApp_tcp = packetSinkHelper_tcp.Install (nodes.Get (3));

  sinkApp_tcp.Start (Seconds (0.0));
  sinkApp_tcp.Stop (Seconds (simulationTime + 0.1));


  OnOffHelper onoff_tcp (socketType, Ipv4Address::GetAny ());
  onoff_tcp.SetAttribute ("OnTime",  StringValue ("ns3::ConstantRandomVariable[Constant=1]"));
  onoff_tcp.SetAttribute ("OffTime", StringValue ("ns3::ConstantRandomVariable[Constant=0]"));
  onoff_tcp.SetAttribute ("DataRate", StringValue ("50Mbps")); //bit/s
  ApplicationContainer apps_tcp;

  InetSocketAddress rmt_tcp (interfaces23.GetAddress (1), port_tcp);
  rmt_tcp.SetTos (0xb8);
  AddressValue remoteAddress_tcp (rmt_tcp);
  onoff_tcp.SetAttribute ("Remote", remoteAddress_tcp);
  apps_tcp.Add (onoff_tcp.Install (nodes.Get (0)));
  apps_tcp.Start (Seconds (1.0));
  apps_tcp.Stop (Seconds (simulationTime + 0.1));

  FlowMonitorHelper flowmon;
  Ptr<FlowMonitor> monitor = flowmon.InstallAll();

  Simulator::Stop (Seconds (simulationTime + 5));
  Simulator::Run ();

  Ptr<Ipv4FlowClassifier> classifier = DynamicCast<Ipv4FlowClassifier> (flowmon.GetClassifier ());
  std::map<FlowId, FlowMonitor::FlowStats> stats = monitor->GetFlowStats ();
  std::cout << std::endl << "*** Flow monitor statistics ***" << std::endl;
  
  for(std::map<FlowId, FlowMonitor::FlowStats>::const_iterator iter = stats.begin() ; iter != stats.end() ; iter++)
  {
    Ipv4FlowClassifier::FiveTuple t = classifier->FindFlow(iter->first);
    
    std::cout << " Flow ID " << iter->first << " src Addr " << t.sourceAddress << " dest Addr " << t.destinationAddress << std::endl;
    std::cout << " Tx Packets " << iter->second.txPackets << std::endl;
    
  }
  
  Simulator::Destroy ();

  return 0;
}