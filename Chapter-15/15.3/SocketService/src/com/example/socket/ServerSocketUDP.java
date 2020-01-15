package com.example.socket;

import java.io.IOException;  
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetAddress;  
import java.net.InetSocketAddress;  
import java.net.SocketException;  
  
public class ServerSocketUDP {  
    private byte[] buffer = new byte[1024];  
      
    private DatagramSocket ds = null;  
  
    private DatagramPacket packet = null;  
  
    private InetSocketAddress socketAddress = null;  
  
    private String orgIp;  
  
    /** 
     * ���캯�����������Ͷ˿�. 
     * @param host ���� 
     * @param port �˿� 
     * @throws Exception 
     */  
    public ServerSocketUDP(String host, int port) throws Exception {  
        socketAddress = new InetSocketAddress(host, port);  
        ds = new DatagramSocket(socketAddress);  
        System.out.println("server start ...");  
    }  
      
    public final String getOrgIp() {  
        return orgIp;  
    }  
  
    /** 
     * ���ó�ʱʱ�䣬�÷���������bind����֮��ʹ��. 
     * @param timeout ��ʱʱ�� 
     * @throws Exception 
     */  
    public final void setSoTimeout(int timeout) throws Exception {  
        ds.setSoTimeout(timeout);  
    }  
  
    /** 
     * ��ó�ʱʱ��. 
     * @return ���س�ʱʱ��. 
     * @throws Exception 
     */  
    public final int getSoTimeout() throws Exception {  
        return ds.getSoTimeout();  
    }  
  
    /** 
     * �󶨼�����ַ�Ͷ˿�. 
     * @param host ����IP 
     * @param port �˿� 
     * @throws SocketException 
     */  
    public final void bind(String host, int port) throws SocketException {  
        socketAddress = new InetSocketAddress(host, port);  
        ds = new DatagramSocket(socketAddress);  
    }  
  
  
    /** 
     * �������ݰ����÷���������߳�����. 
     * @return ���ؽ��յ����ݴ���Ϣ 
     * @throws IOException  
     */  
    public final String receive() throws IOException {  
        packet = new DatagramPacket(buffer, buffer.length);  
        ds.receive(packet);  
        orgIp = packet.getAddress().getHostAddress();  
        String info = new String(packet.getData(), 0, packet.getLength());  
        
        System.out.println("user��" +orgIp + "/"+ info); 
        return info;  
    }  
  
    /** 
     * ����Ӧ�����͸������. 
     * @param bytes ��Ӧ���� 
     * @throws IOException 
     */  
    public final void response(String info) throws IOException {  
        System.out.println(" " + packet.getAddress().getHostAddress()  
                + ",port��" + packet.getPort());  
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet  
                .getAddress(), packet.getPort());  
        dp.setData(info.getBytes());  
        ds.send(dp);  
    }  
  
    /** 
     * ���ñ��ĵĻ��峤��. 
     * @param bufsize ���峤�� 
     */  
    public final void setLength(int bufsize) {  
        packet.setLength(bufsize);  
    }  
  
    /** 
     * ��÷��ͻ�Ӧ��IP��ַ. 
     * @return ���ػ�Ӧ��IP��ַ 
     */  
    public final InetAddress getResponseAddress() {  
        return packet.getAddress();  
    }  
  
    /** 
     * ��û�Ӧ�������Ķ˿�. 
     * @return ���ػ�Ӧ�������Ķ˿�. 
     */  
    public final int getResponsePort() {  
        return packet.getPort();  
    }  
  
    /** 
     * �ر�udp������. 
     */  
    public final void close() {  
        try {  
            ds.close();  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
  
    /**
     * ���Ժ���. 
     */  
    public static void main(String[] args) throws Exception {  
        String serverHost = "192.168.1.104";  
        int serverPort = 9999;  
        ServerSocketUDP udpServerSocket = new ServerSocketUDP(serverHost, serverPort);  
        while (true) {  
            udpServerSocket.receive();  
            udpServerSocket.response("Hello, Android!");  
        }  
    }  
} 