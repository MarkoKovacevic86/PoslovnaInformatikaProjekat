/* 
 * This file is part of Mosquito meta-loader.
 *
 * Mosquito meta-loader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Mosquito meta-loader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rs.mgifos.mosquito.impl.pdm;

import java.util.ArrayList;

/**
 * 
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov
 *         &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class PDMType {

	private ArrayList<LenType> alLengthTypes = new ArrayList<LenType>();

	private ArrayList<PrecType> alPrecTypes = new ArrayList<PrecType>();

	private String defaultJClass = "java.lang.String";

	private String pdTypeName;

	/**
	 * 
	 */
	public PDMType() {
	}

	/**
	 * @param aPDTypeName
	 * @param aDefaultJClass
	 */
	public PDMType(String aPDTypeName, String aDefaultJClass) {
		pdTypeName = aPDTypeName;
		defaultJClass = aDefaultJClass;
	}

	/**
	 * @param aLength
	 * @param aPrecision
	 * @return
	 */
	public String getClassName(int aLength, int aPrecision) {

		for (PrecType crntPrecType : alPrecTypes) {
			if (aLength <= crntPrecType.getLength()) {
				return crntPrecType.getJClass();
			}
		}

		for (LenType crntLenType : alLengthTypes) {
			if (aLength <= crntLenType.getLength()) {
				return crntLenType.getJClass();
			}
		}
		return defaultJClass;
	}

	/**
	 * @return defaultJType.
	 */
	public String getDefaultJClass() {
		return defaultJClass;
	}

	/**
	 * @return pdTypeName.
	 */
	public String getPdTypeName() {
		return pdTypeName;
	}

	/**
	 * @param aDefaultJType
	 *            The defaultJType to set.
	 */
	public void setDefaultJClass(String aDefaultJType) {
		defaultJClass = aDefaultJType;
	}

	/**
	 * @param aPdTypeName
	 *            The pdTypeName to set.
	 */
	public void setPdTypeName(String aPdTypeName) {
		pdTypeName = aPdTypeName;
	}

	/**
	 * @param aPrecType
	 * @return
	 */
	public boolean addPrec(PrecType aPrecType) {
		return alPrecTypes.add(aPrecType);
	}

	/**
	 * @param aLenType
	 * @return
	 */
	public boolean addLen(LenType aLenType) {
		return alLengthTypes.add(aLenType);
	}
}