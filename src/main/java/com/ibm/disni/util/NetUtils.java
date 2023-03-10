/*
 * DiSNI: Direct Storage and Networking Interface
 *
 * Author: Patrick Stuedi <stu@zurich.ibm.com>
 *
 * Copyright (C) 2016-2018, IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ibm.disni.util;

import org.slf4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Byte order in network package and host local buffer is usually different:
 * network use big endian, host use little endian.
 */
public class NetUtils {
	private final static boolean nativeIsNetwork = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
	private static final Logger LOG = DiSNILogger.getLogger();
	
	private NetUtils(){
	}
	
	public static int getIntIPFromName(String name) throws UnknownHostException {
		InetAddress localHost = InetAddress.getByName(name);
		byte[] addr = localHost.getAddress();
		ByteBuffer buffer = ByteBuffer.wrap(addr);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.clear();
		return buffer.getInt();
	}

	public static int getIntIPFromInetAddress(InetAddress localHost) throws UnknownHostException {
		if (localHost == null){
			throw new UnknownHostException("Address not defined");
		}
		byte[] addr = localHost.getAddress();
		ByteBuffer buffer = ByteBuffer.wrap(addr);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		buffer.clear();
		return buffer.getInt();
	}
	
	public static InetAddress getInetAddressFromIntIP(int intIP)
			throws UnknownHostException {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(intIP);
		buffer.clear();
		byte[] addr = new byte[4];
		buffer.get(addr);
		InetAddress address = InetAddress.getByAddress(addr);
		return address;
	}

	/**
	 * short in java occupy 2 bytes
	 *
	 * convert short from host byte order (little-endian) into network byte order (big-endian)
	 * if host native is big-endian the network byte order, will not invoke swap method and return directly.
	 * @param x short type variable from host
	 * @return converted big-endian byte order's short variable
	 */
	public static short hostToNetworkByteOrder(short x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}
		return EndianUtils.swap(x);
	}

	/**
	 * char in java occupy 2 bytes
	 * convert char from host byte order (little-endian) into network byte order (big-endian)
	 * if host native is big-endian the network byte order, will not invoke swap method and return directly.
	 * @param x char type variable from host
	 * @return converted big-endian byte order's short variable
	 */
	public static char hostToNetworkByteOrder(char x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	/**
	 * int in java occupy 4 bytes
	 * convert int from host byte order(little-endian) into network byte order (big-endian)
	 * if host native is big-endian the network byte order, will not invoke swap method and return directly.
	 * @param x int type variable from host
	 * @return converted big-endian byte order's int variable
	 */
	public static int hostToNetworkByteOrder(int x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	/**
	 * long in java occupy 8 bytes
	 * convert long from host byte order(little-endian) into network byte order (big-endian)
	 * if host native is big-endian the network byte order, will not invoke swap method and return directly.
	 * @param x long type variable from host
	 * @return converted big-endian byte order's long variable
	 */
	public static long hostToNetworkByteOrder(long x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}	

	/**
	 * convert short variable from network byte order (big-endian) into host byte order (little-endian)
	 * if host native originally is big-endian return directly without swap method invoking.
	 * @param x short type variable from network package
	 * @return converted big-endian byte order's short variable
	 */
	public static short networkToHostByteOrder(short x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	/**
	 * convert char variable from network byte order (big-endian) into host byte order (little-endian)
	 * if host native is big-endian return directly without swap method invoking.
	 * @param x char type variable from network package
	 * @return converted big-endian byte order's char variable
	 */
	public static char networkToHostByteOrder(char x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	/**
	 * convert int variable from network byte order (big-endian) into host byte order (little-endian)
	 * if host native is big-endian return directly without swap method invoking.
	 * @param x int type variable from network package
	 * @return converted big-endian byte order's int variable
	 */
	public static int networkToHostByteOrder(int x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	/**
	 * convert long variable from network byte order (big-endian) into host byte order (little-endian)
	 * if host native is big-endian return directly without swap method invoking.
	 * @param x long type variable from network package
	 * @return converted big-endian byte order's long variable
	 */
	public static long networkToHostByteOrder(long x) {
		if (nativeIsNetwork){
			LOG.trace("#hostToNetworkByOrder native byte order is network order(Big Endian)");
			return x;
		}		
		return EndianUtils.swap(x);
	}

	public static int getIntIPFromInetAddress(String string) {
		return 0;
	}	
}
