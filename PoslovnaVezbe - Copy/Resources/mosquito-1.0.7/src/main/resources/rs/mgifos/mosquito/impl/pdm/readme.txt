These PowerDesigner PD types and mappings to Java classes are supported:
	- bit
		- java.lang.Boolean
	- char
		- java.lang.String
	- datetime
		- java.util.Date
	- decimal
		- java.lang.Double
	- numeric
		- java.lang.Integer
		- java.lang.Long
		- java.math.bigInteger
	- varchar
		- java.lang.String

In MSSQL are supported also next types:

	- bool
		- java.lang.Boolean
	- date
		- java.util.Date
	- bigint
		- java.math.BigInteger
	- int
		- java.math.BigInteger
	- smallint
		- java.math.BigInteger
	- tinyint
		- java.math.BigInteger
	- float
		- java.lang.Float
	- real
		- java.lang.Float
	- double precision
		- java.math.BigDecimal
	- money
		- java.math.BigDecimal
 	- text
		- java.lang.String

If some mapping doesn't appear in upper list default mapping will be to java.lang.String class.
	
