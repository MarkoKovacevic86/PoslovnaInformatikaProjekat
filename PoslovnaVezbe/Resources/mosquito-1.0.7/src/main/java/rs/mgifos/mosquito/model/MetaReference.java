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

package rs.mgifos.mosquito.model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class MetaReference implements Iterable<MetaColumn> {

    protected String code;

    protected Hashtable<String, MetaColumn> htColumns = new Hashtable<String, MetaColumn>();

    protected String name;

    protected MetaTable sourceTable;

    protected MetaTable parentTable;

    /**
     * 
     */
    public MetaReference() {

    }

    /**
     * @param aName
     * @param aCode
     */
    public MetaReference(String aName, String aCode) {
        name = aName;
        code = aCode;
    }

    /**
     * Adds a new MetaColumn into key group
     * 
     * @param aMetaColumn
     */
    public void addColumn(MetaColumn aMetaColumn) {
        htColumns.put(aMetaColumn.getCode(), aMetaColumn);
    }

    /**
     * @param aMetaColumn
     * @return true if aMetaColumn is part of this MetaKey
     */
    public boolean containsColumn(MetaColumn aMetaColumn) {
        return htColumns.containsValue(aMetaColumn);
    }

    /**
     * @param aColCode
     * @return
     * @throws NullPointerException
     */
    public boolean containsColumnCode(String aColCode)
            throws NullPointerException {
        return htColumns.containsKey(aColCode);
    }

    /**
     * @return Enumeration of all columns of this MetaKey
     */
    public Enumeration<MetaColumn> eColumns() {
        return htColumns.elements();
    }

    /**
     * @return code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param aCode
     * @return MetaColumn if it exists, otherwise returns null
     */
    public MetaColumn getColByCode(String aCode) {
        MetaColumn retVal = null;
        try {
            retVal = (MetaColumn) htColumns.get(aCode);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param aCode
     *            The code to set.
     */
    public void setCode(String aCode) {
        code = aCode;
    }

    /**
     * @param aName
     *            The name to set.
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Get referenced table
     * 
     * @return
     */
    public MetaTable getParentTable() {
        return parentTable;
    }

    /**
     * @param aParentTable
     */
    public void setParentTable(MetaTable aParentTable) {
        parentTable = aParentTable;
    }

    /**
     * @return
     */
    public MetaTable getSourceTable() {
        return sourceTable;
    }

    /**
     * @param aSourceTable
     */
    public void setSourceTable(MetaTable aSourceTable) {
        sourceTable = aSourceTable;
    }

    /**
     * @see java.lang.Iterable#iterator()
     */
    public Iterator<MetaColumn> iterator() {
        return htColumns.values().iterator();
    }
}
