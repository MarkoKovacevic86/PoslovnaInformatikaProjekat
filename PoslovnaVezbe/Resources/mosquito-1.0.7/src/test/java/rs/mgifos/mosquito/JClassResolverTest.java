/* 
 * This file is part of Mosquito meta-loader.
 *
 * Mosquito meta-loader is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Mosquito meta-loader is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package rs.mgifos.mosquito;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.xml.sax.InputSource;

import rs.mgifos.mosquito.impl.pdm.JClassResolver;

/**
 * 
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov
 *         &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class JClassResolverTest {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private JClassResolver resolver = JClassResolver.getInstance();
	
	@Test
	public void checkAllTypes() throws Exception {
		String[][] scenario = new String[][] { { "BOOL", "java.lang.Boolean" },
				{ "CHAR", "java.lang.String" },
				{ "DECIMAL", "java.math.BigDecimal" },
				{ "DATE", "java.sql.Date" },
				{ "NUMBER", "java.math.BigDecimal" },
				{ "NUMERIC", "java.math.BigDecimal" },
				{ "VARCHAR", "java.lang.String" },
				{ "bool", "java.lang.Boolean" },
				{ "char", "java.lang.String" },
				{ "decimal", "java.math.BigDecimal" },
				{ "date", "java.sql.Date" },
				{ "number", "java.math.BigDecimal" },
				{ "numeric", "java.math.BigDecimal" },
				{ "varchar", "java.lang.String" },
				{ "datetime", "java.sql.Date" },
				{ "bit", "java.lang.Boolean" },
				{ "bigint", "java.math.BigInteger" },
				{ "int", "java.math.BigInteger" },
				{ "smallint", "java.math.BigInteger" },
				{ "tinyint", "java.math.BigInteger" },
				{ "float", "java.lang.Float" },
				{ "real", "java.lang.Float" },
				{ "double precision", "java.math.BigDecimal" },
				{ "money", "java.math.BigDecimal" },
				{ "text", "java.lang.String" }
				};

		XPath xpath = XPathFactory.newInstance().newXPath();
		InputSource is = new InputSource(
				"src/main/resources/rs/mgifos/mosquito/impl/pdm/jclass_resolver.xml");

		for (String[] typePair : scenario) {
			String expectedPdmType = typePair[0];
			String expectedJavaType = typePair[1];
			checkTypeMapping(xpath, is, expectedPdmType, expectedJavaType);
			logger.debug("Pdm type <" + expectedPdmType + "> against expected Java type <" + expectedJavaType + "> ... OK");
		}
	}
	
	@Test
	public void simulateResolveMiss() throws Exception {
		String resolvedJavaType = resolver.getJClassName("NON_EXISTING_TYPE", 0, 0);
		logger.debug("Resolved type: " + resolvedJavaType);
		Assert.assertEquals("java.lang.String", resolvedJavaType);
	}

	private void checkTypeMapping(XPath xpath, InputSource is, String pdmType,
			String expectedJavaType) throws Exception {
		String actualJavaType = xpath.evaluate(
				"/JClassResolver/PDMType[@name='" + pdmType
						+ "']/@jDefaultClass", is);
		Assert.assertEquals(expectedJavaType, actualJavaType);
		
		String resolvedJavaType = resolver.getJClassName(pdmType, 0, 0);
		Assert.assertEquals(expectedJavaType, resolvedJavaType);
	}
}
