/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedWriter;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.FileWriter;

import java.util.Iterator;

/**
 * <a href="ValueMapperUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class ValueMapperUtil {

	public static void persist(
			ValueMapper valueMapper, String tmpDir, String fileName)
		throws Exception {

		FileUtil.mkdirs(tmpDir);

		UnsyncBufferedWriter bw = new UnsyncBufferedWriter(
			new FileWriter(tmpDir + "/" + fileName + ".txt"));

		try {
			Iterator<Object> itr = valueMapper.iterator();

			while (itr.hasNext()) {
				Object oldValue = itr.next();

				Object newValue = valueMapper.getNewValue(oldValue);

				bw.write(oldValue + "=" + newValue);

				if (itr.hasNext()) {
					bw.write("\n");
				}
			}
		}
		finally {
			bw.close();
		}
	}

}