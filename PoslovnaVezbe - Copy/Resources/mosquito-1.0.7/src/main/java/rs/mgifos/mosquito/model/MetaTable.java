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
 * Class represent of DB Table's meta data
 *
 * @author <a href="mailto:nikola.petkov@gmail.com">Nikola Petkov &lt;nikola.petkov@gmail.com&gt;</a>
 */
public class MetaTable implements Iterable<MetaColumn> {

    protected String code;

    protected String comment;

    protected Hashtable<String, MetaColumn> htColumns = new Hashtable<String, MetaColumn>();

    protected Hashtable<String, MetaKey> htKeys = new Hashtable<String, MetaKey>();

    protected Hashtable<String, MetaReference> htRefs = new Hashtable<String, MetaReference>();

    protected String name;

    protected Vector<MetaColumn> vColumns = new Vector<MetaColumn>();

    /**
     * @param aCode
     *            code
     * @param aName
     *            name
     * @param aComment
     *            comment about this MetaTable
     */
    public MetaTable(String aCode, String aName, String aComment) {
        super();
        code = aCode;
        name = aName;
        comment = aComment;
    }

    /**
     * Adds the new MetaColumn into this MetaTable container
     * 
     * @param aMC
     */
    public void addCol(MetaColumn aMC) {
        aMC.setParentTable(code);
        Enumeration<MetaKey> eKeys = htKeys.elements();
        while (eKeys.hasMoreElements()) {
            MetaKey mk = (MetaKey) eKeys.nextElement();
            if (mk.containsColumn(aMC))
                aMC.setParentKey(mk);
        }
        vColumns.add(aMC);
        htColumns.put(aMC.getTableDotColumnCode(), aMC);
    }

    /**
     * @param aMK
     */
    public void addKey(MetaKey aMK) {
        htKeys.put(aMK.getCode(), aMK);
    }

    /**
     * @param aMR
     */
    public void addRef(MetaReference aMR) {
        htRefs.put(aMR.getCode(), aMR);
    }

    /**
     * Returns collection of MetaColumn(s) of this MetaTable
     * 
     * @return collection of MetaColumn(s) of this MetaTable
     */
    public Collection cColumns() {
        return vColumns;
    }

    /**
     * Checks for specified MetaColumn persistaion within this MetaTable
     * 
     * @param aMC
     *            MetaColumn which persistation is being checked
     * @return true if specified MetaColumn exist within this MetaTable
     */
    public boolean contains(MetaColumn aMC) {
        return htColumns.contains(aMC);
    }

    /**
     * Returns enumeration of MetaColums of this MetaTable
     * 
     * @return enumeration of MetaColums of this MetaTable
     */
    public Enumeration eColumns() {
        return vColumns.elements();
    }

    /**
     * @return Enumeration of MetaKeys
     */
    public Enumeration eKeys() {
        return htKeys.elements();
    }

    /**
     * @return Enumeration of References
     */
    public Enumeration eRefs() {
        return htRefs.elements();
    }

    /**
     * Returns code of this MetaTable
     * 
     * @return code of this MetaTable
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns MetaColumn of this MetaTable by specified
     * column.getTabledotColumnCode()
     * 
     * @param aTableDotColumnCode
     *            specified code
     * @return MetaColumn of this MetaTable by specified
     *         column.getTabledotColumnCode() if exists, otherwise it returns
     *         null
     * @throws NullPointerException
     *             if aCode is null
     */
    public Object getColByTableDotColumnCode(String aTableDotColumnCode)
            throws NullPointerException {
        return htColumns.get(aTableDotColumnCode);
    }

    /**
     * Returns description of this MetaTable
     * 
     * @return description of this MetaTable
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param aKeyCode
     * @return MetaKey specifed by a aKeyCode, or null if it doesn't exist
     */
    public MetaKey getKeyByCode(String aKeyCode) {
        MetaKey retVal = null;
        try {
            retVal = (MetaKey) htKeys.get(aKeyCode);
        } catch (Exception e) {
            retVal = null;
        }
        return retVal;
    }

    /**
     * Returns name of this MetaTable
     * 
     * @return name of this MetaTable
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the count of all MetaColumn(s) within this MetaTable
     * 
     * @return the count of all MetaColumn(s) within this MetaTable
     */
    public int getTotalColumns() {
        return vColumns.size();
    }

    public Iterator<MetaColumn> iterator() {
        //return htColumns.values().iterator();
        return vColumns.iterator();
    }

    /**
     * Sets code for this MetaTable
     * 
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets description of this MetaTable
     * 
     * @param aDescription
     *            the description to set
     */
    public void setComment(String aDescription) {
        comment = aDescription;
    }

    /**
     * Sets name for this MetaTable
     * 
     * @param name
     *            the name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns string representation of this MetaTable (name)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return name;
    }

    /**
     * Returns verbose description of this MetaTable (HTML) - recursive fks
     * 
     * @return verbose description of this MetaTable
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
                + "<TD align=\"left\" ROWSPAN=\"2\">"
                + MetaUtils.insertHTMLBreaks(comment, 50) + "</TD></TR>";
        retVal += "</TBODY></TABLE>";
        // KEYS
        retVal += "<TABLE border=\"1\">" + "<CAPTION><B> KEYS"
                + "</B></CAPTION>";
        retVal += "<TH><I>Name</I></TH><TH><I>Code</I></TH><TH><I>Columns</I></TH><TBODY>";
        Enumeration<MetaKey> eKeys = htKeys.elements();
        while (eKeys.hasMoreElements()) {
            MetaKey crntKey = eKeys.nextElement();
            retVal += "<TR><TD>";
            retVal += crntKey.getName();
            retVal += "</TD><TD>";
            retVal += crntKey.getCode();
            retVal += "</TD><TD><TABLE><TBODY>";
            Enumeration<MetaColumn> eKeyCols = crntKey.eColumns();
            while (eKeyCols.hasMoreElements()) {
                MetaColumn crntKeyCol = eKeyCols.nextElement();
                retVal += "<TR><TD>" + crntKeyCol.getCode() + "</TD><TD>";
                retVal += crntKeyCol.getName() + "</TD></TR>";
            }
            retVal += "</TBODY></TABLE>";
        }
        retVal += "</TBODY></TABLE>";

        // REFERENCES
        retVal += "<TABLE border=\"1\">" + "<CAPTION><B> REFERENCES"
                + "</B></CAPTION>";
        retVal += "<TH><I>Name</I></TH><TH><I>Code</I></TH><TH><I>Parent Table</I></TH>"
                + "<TH><I>Columns</I></TH><TBODY>";
        Enumeration<MetaReference> eRefs = htRefs.elements();
        while (eRefs.hasMoreElements()) {
            MetaReference currRef = eRefs.nextElement();
            retVal += "<TR><TD>";
            retVal += currRef.getName();
            retVal += "</TD><TD>";
            retVal += currRef.getCode();
            retVal += "</TD><TD>";
            retVal += currRef.getParentTable();
            retVal += "</TD><TD><TABLE><TBODY>";
            Enumeration<MetaColumn> eKeyCols = currRef.eColumns();
            while (eKeyCols.hasMoreElements()) {
                MetaColumn crntKeyCol = eKeyCols.nextElement();
                retVal += "<TR><TD>" + crntKeyCol.getCode() + "</TD><TD>";
                retVal += crntKeyCol.getName() + "</TD></TR>";
            }
            retVal += "</TD></TBODY></TABLE>";
        }
        retVal += "</TBODY></TABLE>";

        // COLUMNS
        retVal += "<TABLE border=\"1\">" + "<CAPTION><B> COLUMNS"
                + "</B></CAPTION>";
        retVal += "<TH><I>No</I></TH><TH><I>Name</I></TH><TH><I>Code</I></TH><TBODY>";
        int count = 0;
        Enumeration<MetaColumn> eCols = vColumns.elements();
        while (eCols.hasMoreElements()) {
            MetaColumn crntCol = (MetaColumn) eCols.nextElement();
            retVal += "<TR><TD align=\"right\">";
            retVal += ++count;
            retVal += "</TD><TD>";
            retVal += crntCol.getName();
            retVal += "</TD><TD>";
            retVal += crntCol.getCode();
            retVal += "</TD></TR>";
        }
        retVal += "</TBODY></TABLE></HTML>";

        return retVal;
    }
}