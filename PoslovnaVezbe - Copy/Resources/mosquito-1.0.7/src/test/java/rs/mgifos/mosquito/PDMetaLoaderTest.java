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

import java.util.Properties;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Test;

import rs.mgifos.mosquito.impl.pdm.PDMetaLoader;
import rs.mgifos.mosquito.model.MetaColumn;
import rs.mgifos.mosquito.model.MetaModel;
import rs.mgifos.mosquito.model.MetaTable;

/**
 * 
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov
 *         &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class PDMetaLoaderTest {

	private Logger logger = Logger.getLogger(PDMetaLoaderTest.class);

	private Properties createNonExistingFileProperties() {
		Properties retval = new Properties();
		retval.put(PDMetaLoader.FILENAME, "THIS IS NON EXISTING FILE");
		return retval;
	}

	private Properties createProperties() {
		Properties retval = new Properties();
		retval.put(PDMetaLoader.FILENAME,
				"src/test/resources/meta-test-v12.pdm");
		return retval;
	}

	@Test
	public void getMetaModel() throws Exception {
		IMetaLoader metaLoader = new PDMetaLoader();

		MetaModel model = metaLoader.getMetaModel(createProperties());
		logger.info("Meta model successfully retrieved!");

		Assert.assertEquals("META", model.getCode());
		Assert.assertEquals(18, model.getColumnCount());
		Assert.assertEquals("", model.getComment());
		Assert.assertEquals("meta", model.getName());
		Assert.assertEquals(5, model.getTableCount());

		logger.info("Asserting meta model: " + model.getCode());
		
		for (MetaTable table : model) {
			String tableCode = table.getCode();
			
			assertTable(tableCode, model);

			Assert.assertNotNull(model.getTableByCode(tableCode));
		}
		
	}

	@Test(expected = LoadingException.class)
	public void notSuchFileGetMetamodel() throws LoadingException {
		IMetaLoader metaLoader = new PDMetaLoader();
		metaLoader.getMetaModel(createNonExistingFileProperties());
	}
	
	private void assertTable(String tableCode, MetaModel model) {
		
		logger.info("    Asserting table[code]: " + tableCode);
		
		MetaTable table = model.getTableByCode(tableCode);
		Assert.assertNotNull(table);	
		
		
		for (MetaColumn column : table) {
			Assert.assertEquals(null, column.getMinValue());
			Assert.assertEquals(null, column.getMaxValue());
			Assert.assertEquals("", column.getFormat());
			Assert.assertEquals(false, column.isCannotModify());
			
			logger.debug("        Column [code]: " + column.getCode());
			logger.debug("            - name: " + column.getName());
			logger.debug("            - jClassName: " + column.getJClassName());
			logger.debug("            - length: " + column.getLength());
			logger.debug("            - precision: " + column.getPrecision());
			logger.debug("            - comment: " + column.getComment());
			logger.debug("            - defaultVal: " + column.getDefaultVal());
			
			Assert.assertSame(column, table.getColByTableDotColumnCode(table.getCode() + "." + column.getCode()));
		}
	}
}
