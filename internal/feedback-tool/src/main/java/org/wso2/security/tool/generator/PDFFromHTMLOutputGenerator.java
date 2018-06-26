/*
 * Copyright (c) 2018, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.security.tool.generator;

import com.lowagie.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.wso2.security.tool.util.Constants;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * PDFFromHTMLOutputGenerator -- This class consists of functionality to generate an output pdf file by
 * reading an html file. The method generate() will generate a pdf file from the given html string. The html string
 * is read from a .html file using the method readHTMLFile().
 *
 * @author Arshika Mohottige
 */
public class PDFFromHTMLOutputGenerator implements OutputGenerator {

    private static final Logger log = LoggerFactory.getLogger(PDFFromHTMLOutputGenerator.class);
    private String htmlFilePath;
    private String htmlString;

    /**
     * Constrictor to set the value of the variable htmlFilePath.
     *
     * @param htmlFilePath
     */
    public PDFFromHTMLOutputGenerator(String htmlFilePath) {
        this.htmlFilePath = htmlFilePath;
    }

    /**
     * Getter for the variable htmlFilePath.
     *
     * @return the value of the variable htmlFilePath.
     */
    public String getHtmlFilePath() {
        return htmlFilePath;
    }

    /**
     * Getter for the variable htmlString.
     *
     * @return the value of the variable htmlString.
     */
    public String getHtmlString() {
        return htmlString;
    }

    /**
     * Setter for the variable htmlString.
     *
     * @param htmlString
     */
    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }

    /**
     * Generates an output PDF file by reading and converting HTML file.
     * The pre - generated or uploaded HTML file is read through the method readHTMLFile() and an html string is
     * extracted. The html string is then rendered to a PDF file by the method generate() creating an output
     * PDF file.
     *
     * @param outputFilePath The output file path where the output pdf file is created.
     * @throws IOException If the FileOutputStream fails to find the output file in the given path.
     */
    @Override
    public void generate(String outputFilePath) throws IOException {
        try {
            readHTMLFile();
        } catch (IOException e) {
            log.error("IOException was thrown file reading the HTML file; " + e.getMessage(), e);
        }
        new File(outputFilePath + Constants.OUTPUT_PDF_FILE);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.getHtmlString()
                .getBytes(StandardCharsets.UTF_8))) {
            Document document = XMLResource.load(byteArrayInputStream).getDocument();
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocument(document, "/");
            renderer.layout();

            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath +
                    Constants.OUTPUT_PDF_FILE)) {
                renderer.createPDF(fileOutputStream);
            } catch (DocumentException e) {
                log.error("DocumentException was thrown while generating the PDF file; " + e.getMessage(), e);
            }
        }
    }

    /**
     * Reads the HTML file at htmlFilePath and generates an html string.
     * The HTML file at htmlFilePath is read and the generated html string is then set to the variable
     * htmlString.
     *
     * @throws IOException
     */
    public void readHTMLFile() throws IOException {
        File file = new File(this.getHtmlFilePath());
        if (!file.exists()) {
            log.error("The html file is not found in the given path");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlFilePath))) {
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            this.setHtmlString(sb.toString());
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException was thrown while reading the html file; " + e.getMessage(), e);
        }
    }
}
