package com.bd17kaka.picindex.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.FSDirectory;

import net.semanticmetadata.lire.DocumentBuilder;
import net.semanticmetadata.lire.DocumentBuilderFactory;

/**
 * 索引图片类
 * 
 * @author Bd17kaka
 * 
 */
public class testPicIndex {

	private String[] testFiles = new String[] { "img01.JPG", "img02.JPG",
			"img03.JPG", "img04.JPG", "img05.JPG" };
	private String testFilesPath = "./src/test/resources/images/";
	private String indexPath = "test-index";
	private String testExtensive = "../Caliph/testdaten";

	
	public void testCreateIndex() throws IOException {
		// Create an appropriate DocumentBuilder
		DocumentBuilder builder = DocumentBuilderFactory
				.getDefaultDocumentBuilder();
		// That's the way it is done with Lucene 3.0 - supported with LIRe v0.8
		IndexWriter iw = new IndexWriter(
				FSDirectory.open(new File(indexPath)),
				new SimpleAnalyzer(), true,
				IndexWriter.MaxFieldLength.UNLIMITED);
		for (String identifier : testFiles) {
			// Build the Lucene Documents
			Document doc = builder.createDocument(new FileInputStream(
					testFilesPath + identifier), identifier);
			// Add the Documents to the index
			iw.addDocument(doc);
		}
		iw.optimize();
		iw.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}