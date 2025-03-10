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

import java.nio.ByteBuffer;

/**
 * MemBuf is basic unit that can be allocated && free by MemoryAllocation.
 * MemBuf encapsulate ByteBuffer and the direct address in long of the allocated address.
 */
public class MemBuf {
	private long address;
	private ByteBuffer buffer;
	private MemoryAllocation memAlloc;

	MemBuf(ByteBuffer buffer, MemoryAllocation memAlloc) {
		this.buffer = buffer;
		this.address = MemoryUtils.getAddress(buffer);
		this.memAlloc = memAlloc;
	}

	/**
	 * method to get the direct address of the allocated ByteBuffer.
	 */
	public final long address() {
		return address;
	}
	
	//----------------------------
	
	public final ByteBuffer getBuffer() {
		return buffer;
	}

	public final int size() { return buffer.capacity(); }

	/**
	 * free method will not release the MemBuf#ByteBuffer allocated space,
	 * instead it reset the already allocated ByteBuffer's position, capacity and mark those inner variables,
	 * after reset those attributes it adds the MemBuf#size, MemBuf as key value pair to the table.
	 */
	public void free() {
		if (memAlloc != null) {
			memAlloc.free(this);
		}
	}

}
