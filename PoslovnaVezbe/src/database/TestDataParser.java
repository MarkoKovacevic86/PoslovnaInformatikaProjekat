package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestDataParser {
	public TestDataParser(){parseData();}
	
	public void parseData(){
		System.out.println("Parsing test data ****");
		parseFile();
	}
	
	private String parseFile(){
		try {
			FileReader fr = new FileReader(new File("src/resources/testdata.sql"));
			BufferedReader br = new BufferedReader(fr);
			//StringBuffer sb = new StringBuffer();
			try {
				while(br.readLine() != null){
					executeParsedStatement(br.readLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("***ERROR cannot find specified file");
			e.printStackTrace();
		}
		return null;
		
	}
	

	
	private void executeParsedStatement(String stmt){
		
	}
}
