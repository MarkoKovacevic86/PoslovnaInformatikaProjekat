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
 * 
 */

package rs.mgifos.mosquito.impl.pdm;

import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Java class name resolver. Mapping is described within the XML file
 * jclass_resolver.xml.
 * 
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov
 *         &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class JClassResolver {

	private Logger logger = Logger.getLogger(this.getClass());

	class SaxHandler extends DefaultHandler {
		private static final String PDM_TYPE = "PDMType";

		private static final String PREC = "Prec";

		private static final String LEN = "Len";

		private PDMType actualPDMType = null;

		public void startElement(String aUri, String aLocalName, String aQName,
				Attributes attributes) throws SAXException {
			try {
				if (PDM_TYPE.equals(aQName)) {
					String name = attributes.getValue("name");
					String jDefaultClass = attributes.getValue("jDefaultClass");
					actualPDMType = new PDMType(name, jDefaultClass);
					ht.put(name, actualPDMType);
				} else if (PREC.equals(aQName)) {
					String length = attributes.getValue("length");
					String jClass = attributes.getValue("jClass");
					PrecType pt = new PrecType(Integer.parseInt(length), jClass);
					actualPDMType.addPrec(pt);
				} else if (LEN.equals(aQName)) {
					String length = attributes.getValue("length");
					String jClass = attributes.getValue("jClass");
					LenType lt = new LenType(Integer.parseInt(length), jClass);
					actualPDMType.addLen(lt);
				}
			} catch (Exception e) {
				logger.error("Sax Parsing xml failed!");
			}
		}
	}

	private static JClassResolver INSTANCE = null;

	/**
	 * Returns singleton's instance
	 * 
	 * @return singleton's instance
	 */
	public static JClassResolver getInstance() {
		if (INSTANCE == null)
			INSTANCE = new JClassResolver();
		return INSTANCE;
	}

	private Hashtable<String, PDMType> ht = new Hashtable<String, PDMType>();

	private JClassResolver() {
		super();
		this.init();
	}

	/**
	 * @param aType
	 * @param aLength
	 * @param aPrecision
	 * @return Java class name for the specified input type (one of the T_XXX)
	 */
	public String getJClassName(String aType, int aLength, int aPrecision) {
		String retVal = "java.lang.String";
		try {
			retVal = ((PDMType) ht.get(aType))
					.getClassName(aLength, aPrecision);
		} catch (Exception e) {
			logger.warn("Can't resolve type: '" + aType + "'. Check the existence of this type in jclass_resolver.xml file! Default JClass is java.lang.String!");
			retVal = "java.lang.String";
		}
		return retVal;
	}

	private void init() {
		try {
			InputStream is = JClassResolver.class
					.getResourceAsStream("jclass_resolver.xml");
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(is, new SaxHandler());

		} catch (Exception e) {
			logger.error("Can't init resolver! Cause: " + e.getMessage());
		}
	}
}