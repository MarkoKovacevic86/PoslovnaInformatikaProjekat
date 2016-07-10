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

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class MetaModel implements Iterable<MetaTable> {

    protected String code;

    protected String comment;

    protected Hashtable<String, MetaTable> htTables = new Hashtable<String, MetaTable>();

    protected Vector<MetaTable> vTables = new Vector<MetaTable>();

    protected String name;

    /**
     * @param aCode
     *            code
     * @param aName
     *            name
     * @param aComment
     *            comment for this MetaModel
     */
    public MetaModel(String aCode, String aName, String aComment) {
        super();
        code = aCode;
        name = aName;
        comment = aComment;
    }

    /**
     * @param aTable
     *            MetaTable to add
     * @throws NullPointerException
     */
    public void addTable(MetaTable aTable) throws NullPointerException {
        vTables.add(aTable);
        htTables.put(aTable.getCode(), aTable);
    }

    /**
     * Returns collection of tables
     * 
     * @return collection of tables
     */
    public Collection cTables() {
        return vTables;
    }

    /**
     * Returns enumeration of sorted tables by name
     * 
     * @return enumeration of sorted tables by name
     */
    public Enumeration<MetaTable> eSortedTables() {
        Vector<MetaTable> retVal = new Vector<MetaTable>();
        if (!vTables.isEmpty()) {
            Enumeration<MetaTable> eTables = vTables.elements();
            MetaTable crntTable = eTables.nextElement();
            retVal.add(crntTable);
            while (eTables.hasMoreElements()) {
                crntTable = (MetaTable) eTables.nextElement();
                Enumeration<MetaTable> eVTables = retVal.elements();
                int index = 0;
                while (eVTables.hasMoreElements()) {
                    MetaTable crntVTable = eVTables.nextElement();
                    if (crntTable.getName().compareToIgnoreCase(
                            crntVTable.getName()) < 0)
                        break;
                    index += 1;
                }
                retVal.add(index, crntTable);
            }
        }
        return retVal.elements();
    }

    /**
     * Returns enumeration of tables
     * 
     * @return enumeration of tables
     */
    public Enumeration eTables() {
        return vTables.elements();
    }

    /**
     * Returns code of this MetaModel
     * 
     * @return code of this MetaModel
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns total columns of all tables within this MetaModel
     * 
     * @return total columns of all tables within this MetaModel
     */
    public int getColumnCount() {
        int retVal = 0;
        Enumeration<MetaTable> eTables = vTables.elements();
        while (eTables.hasMoreElements()) {
            MetaTable crntTable = eTables.nextElement();
            retVal += crntTable.getTotalColumns();
        }
        return retVal;
    }

    /**
     * Returns description of this MetaModel
     * 
     * @return description of this MetaModel
     */
    public String getComment() {
        return comment;
    }

    /**
     * Returns name of this MetaModel
     * 
     * @return name of this MetaModel
     */
    public String getName() {
        return name;
    }

    /**
     * Returns reference of MetaTable with specified code
     * 
     * @param aCode
     *            code of MetaTable
     * @return reference of MetaTable with specified code
     * @throws NullPointerException
     */
    public MetaTable getTableByCode(String aCode) throws NullPointerException {
        return (MetaTable) htTables.get(aCode);
    }

    /**
     * Returns total count of all MetaTable(s) within this MetaModel
     * 
     * @return total count of all MetaTable(s) within this MetaModel
     */
    public int getTableCount() {
        return htTables.size();
    }

    /**
     * Sets the code of this MetaModel
     * 
     * @param aCode
     *            code to set
     */
    public void setCode(String aCode) {
        code = aCode;
    }

    /**
     * Sets description of this MetaModel
     * 
     * @param aDescription
     *            the description to set
     */
    public void setComment(String aDescription) {
        comment = aDescription;
    }

    /**
     * Sets the name of this MetaModel
     * 
     * @param aName
     *            The name to set.
     */
    public void setName(String aName) {
        name = aName;
    }

    /**
     * Returns the string representation of this MetaModel
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return name;
    }

    /**
     * Returns verbose description of this MetaModel
     * 
     * @return verbose description of this MetaModel
     */
    public String toStringVerbose() {
        String retVal = "<HTML><TABLE border=\"1\">" + "<CAPTION><B>" + code
                + "</B></CAPTION>";
        retVal += "<TBODY>";
        retVal += "<TR><TD align=\"right\">" + "<B>name</B>" + "</TD>"
                + "<TD align=\"left\">" + name + "</TD></TR>";
        retVal += "<TR><TD align=\"right\">" + "<B>code</B>" + "</TD>"
                + "<TD align=\"left\">" + code + "</TD></TR>";
        retVal += "<TR><TD align=\"right\">" + "<B>comment</B>" + "</TD>"
                + "<TD align=\"left\">"
                + MetaUtils.insertHTMLBreaks(comment, 50) + "</TD></TR>";
        retVal += "</TBODY></TABLE>";
        retVal += "<TABLE border=\"1\">" + "<CAPTION><B> TABLES"
                + "</B></CAPTION><TBODY>";
        retVal += "<TH><I>No.</I></TH><TH><I>Name</I></TH><TH><I>Code</I></TH>";
        int count = 0;
        Enumeration<MetaTable> eTables = this.eSortedTables();
        while (eTables.hasMoreElements()) {
            MetaTable crntTable = (MetaTable) eTables.nextElement();
            retVal += "<TR><TD align=\"right\">";
            retVal += ++count;
            retVal += "</TD><TD>";
            retVal += crntTable.getName();
            retVal += "</TD><TD>";
            retVal += crntTable.getCode();
            retVal += "</TD></TR>";
        }
        retVal += "</TBODY></TABLE></HTML>";
        return retVal;
    }

    public Iterator<MetaTable> iterator() {
        return vTables.iterator();
    }
}