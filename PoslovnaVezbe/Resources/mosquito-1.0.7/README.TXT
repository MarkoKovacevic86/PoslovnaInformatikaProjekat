The Mosquito Meta Loader, release 1.0.5 (September 2007)

1. INTRODUCTION

Mosquito is an open source library for parsing Power Designer PDM files and 
converting them to the in-memory structure based on object model provided by 
this library. It is quite useful for relational model based code generation. 

2. USAGE EXAMPLE

		IMetaLoader metaLoader = new PDMetaLoader();

		Properties props = new Properties();
		props.put("file", "meta-test-v12.pdm");

		MetaModel model = metaLoader.getMetaModel(props);
		
	For more information, please refer to junit tests.

3. BUILD

 - Download source distribution
 - Enter the project directory (mosquito)
 - Run the following command from your console:
   
   mvn clean package
   
 - target folder should contain mosquito-${version}-bin.zip and mosquito-${version}-src.zip files.
 