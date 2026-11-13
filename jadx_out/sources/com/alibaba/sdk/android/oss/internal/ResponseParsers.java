package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes.dex */
public final class ResponseParsers {
    public static final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    public static final class AbortMultipartUploadResponseParser extends AbstractResponseParser<AbortMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AbortMultipartUploadResult parseData(Response response, AbortMultipartUploadResult abortMultipartUploadResult) throws IOException {
            return abortMultipartUploadResult;
        }
    }

    public static final class DeleteBucketResponseParser extends AbstractResponseParser<DeleteBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketResult parseData(Response response, DeleteBucketResult deleteBucketResult) throws IOException {
            return deleteBucketResult;
        }
    }

    public static final class DeleteObjectResponseParser extends AbstractResponseParser<DeleteObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteObjectResult parseData(Response response, DeleteObjectResult deleteObjectResult) throws IOException {
            return deleteObjectResult;
        }
    }

    public static final class PutObjectResponseParser extends AbstractResponseParser<PutObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutObjectResult parseData(Response response, PutObjectResult putObjectResult) throws IOException {
            putObjectResult.setETag(ResponseParsers.trimQuotes(response.header("ETag")));
            if (response.body().contentLength() > 0) {
                putObjectResult.setServerCallbackReturnBody(response.body().string());
            }
            return putObjectResult;
        }
    }

    public static final class AppendObjectResponseParser extends AbstractResponseParser<AppendObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AppendObjectResult parseData(Response response, AppendObjectResult appendObjectResult) throws IOException {
            String strHeader = response.header(OSSHeaders.OSS_NEXT_APPEND_POSITION);
            if (strHeader != null) {
                appendObjectResult.setNextPosition(Long.valueOf(strHeader));
            }
            appendObjectResult.setObjectCRC64(response.header(OSSHeaders.OSS_HASH_CRC64_ECMA));
            return appendObjectResult;
        }
    }

    public static final class HeadObjectResponseParser extends AbstractResponseParser<HeadObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public HeadObjectResult parseData(Response response, HeadObjectResult headObjectResult) throws IOException {
            headObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(headObjectResult.getResponseHeader()));
            return headObjectResult;
        }
    }

    public static final class GetObjectResponseParser extends AbstractResponseParser<GetObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public boolean needCloseResponse() {
            return false;
        }

        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetObjectResult parseData(Response response, GetObjectResult getObjectResult) throws IOException {
            getObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(getObjectResult.getResponseHeader()));
            getObjectResult.setContentLength(response.body().contentLength());
            getObjectResult.setObjectContent(response.body().byteStream());
            return getObjectResult;
        }
    }

    public static final class CopyObjectResponseParser extends AbstractResponseParser<CopyObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CopyObjectResult parseData(Response response, CopyObjectResult copyObjectResult) throws Exception {
            return ResponseParsers.parseCopyObjectResponseXML(response.body().byteStream(), copyObjectResult);
        }
    }

    public static final class CreateBucketResponseParser extends AbstractResponseParser<CreateBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CreateBucketResult parseData(Response response, CreateBucketResult createBucketResult) throws IOException {
            if (createBucketResult.getResponseHeader().containsKey("Location")) {
                createBucketResult.bucketLocation = createBucketResult.getResponseHeader().get("Location");
            }
            return createBucketResult;
        }
    }

    public static final class GetBucketACLResponseParser extends AbstractResponseParser<GetBucketACLResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketACLResult parseData(Response response, GetBucketACLResult getBucketACLResult) throws Exception {
            return ResponseParsers.parseGetBucketACLResponse(response.body().byteStream(), getBucketACLResult);
        }
    }

    public static final class ListObjectsResponseParser extends AbstractResponseParser<ListObjectsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListObjectsResult parseData(Response response, ListObjectsResult listObjectsResult) throws Exception {
            return ResponseParsers.parseObjectListResponse(response.body().byteStream(), listObjectsResult);
        }
    }

    public static final class InitMultipartResponseParser extends AbstractResponseParser<InitiateMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public InitiateMultipartUploadResult parseData(Response response, InitiateMultipartUploadResult initiateMultipartUploadResult) throws Exception {
            return ResponseParsers.parseInitMultipartResponseXML(response.body().byteStream(), initiateMultipartUploadResult);
        }
    }

    public static final class UploadPartResponseParser extends AbstractResponseParser<UploadPartResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public UploadPartResult parseData(Response response, UploadPartResult uploadPartResult) throws IOException {
            uploadPartResult.setETag(ResponseParsers.trimQuotes(response.header("ETag")));
            return uploadPartResult;
        }
    }

    public static final class CompleteMultipartUploadResponseParser extends AbstractResponseParser<CompleteMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CompleteMultipartUploadResult parseData(Response response, CompleteMultipartUploadResult completeMultipartUploadResult) throws Exception {
            if (response.header("Content-Type").equals("application/xml")) {
                return ResponseParsers.parseCompleteMultipartUploadResponseXML(response.body().byteStream(), completeMultipartUploadResult);
            }
            if (response.body() == null) {
                return completeMultipartUploadResult;
            }
            completeMultipartUploadResult.setServerCallbackReturnBody(response.body().string());
            return completeMultipartUploadResult;
        }
    }

    public static final class ListPartsResponseParser extends AbstractResponseParser<ListPartsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListPartsResult parseData(Response response, ListPartsResult listPartsResult) throws Exception {
            return ResponseParsers.parseListPartsResponseXML(response.body().byteStream(), listPartsResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CopyObjectResult parseCopyObjectResponseXML(InputStream inputStream, CopyObjectResult copyObjectResult) throws ParserConfigurationException, SAXException, IOException, ParseException {
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("LastModified")) {
                    copyObjectResult.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(nodeItem)));
                } else if (nodeName.equals("ETag")) {
                    copyObjectResult.setEtag(checkChildNotNullAndGetValue(nodeItem));
                }
            }
        }
        return copyObjectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListPartsResult parseListPartsResponseXML(InputStream inputStream, ListPartsResult listPartsResult) throws ParserConfigurationException, SAXException, IOException, ParseException {
        String strCheckChildNotNullAndGetValue;
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[parseObjectListResponse] - " + documentElement.getNodeName());
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Bucket")) {
                    listPartsResult.setBucketName(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("Key")) {
                    listPartsResult.setKey(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("UploadId")) {
                    listPartsResult.setUploadId(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("PartNumberMarker")) {
                    String strCheckChildNotNullAndGetValue2 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue2 != null) {
                        listPartsResult.setPartNumberMarker(Integer.valueOf(strCheckChildNotNullAndGetValue2).intValue());
                    }
                } else if (nodeName.equals("NextPartNumberMarker")) {
                    String strCheckChildNotNullAndGetValue3 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue3 != null) {
                        listPartsResult.setNextPartNumberMarker(Integer.valueOf(strCheckChildNotNullAndGetValue3).intValue());
                    }
                } else if (nodeName.equals("MaxParts")) {
                    String strCheckChildNotNullAndGetValue4 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue4 != null) {
                        listPartsResult.setMaxParts(Integer.valueOf(strCheckChildNotNullAndGetValue4).intValue());
                    }
                } else if (nodeName.equals("IsTruncated")) {
                    String strCheckChildNotNullAndGetValue5 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue5 != null) {
                        listPartsResult.setTruncated(Boolean.valueOf(strCheckChildNotNullAndGetValue5).booleanValue());
                    }
                } else if (nodeName.equals("StorageClass")) {
                    String strCheckChildNotNullAndGetValue6 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue6 != null) {
                        listPartsResult.setStorageClass(strCheckChildNotNullAndGetValue6);
                    }
                } else if (nodeName.equals("Part")) {
                    NodeList childNodes2 = nodeItem.getChildNodes();
                    PartSummary partSummary = new PartSummary();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node nodeItem2 = childNodes2.item(i2);
                        String nodeName2 = nodeItem2.getNodeName();
                        if (nodeName2 != null) {
                            if (nodeName2.equals("PartNumber")) {
                                String strCheckChildNotNullAndGetValue7 = checkChildNotNullAndGetValue(nodeItem2);
                                if (strCheckChildNotNullAndGetValue7 != null) {
                                    partSummary.setPartNumber(Integer.valueOf(strCheckChildNotNullAndGetValue7).intValue());
                                }
                            } else if (nodeName2.equals("LastModified")) {
                                partSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(nodeItem2)));
                            } else if (nodeName2.equals("ETag")) {
                                partSummary.setETag(checkChildNotNullAndGetValue(nodeItem2));
                            } else if (nodeName2.equals("Size") && (strCheckChildNotNullAndGetValue = checkChildNotNullAndGetValue(nodeItem2)) != null) {
                                partSummary.setSize(Integer.valueOf(strCheckChildNotNullAndGetValue).intValue());
                            }
                        }
                    }
                    arrayList.add(partSummary);
                }
            }
        }
        listPartsResult.setParts(arrayList);
        return listPartsResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream inputStream, CompleteMultipartUploadResult completeMultipartUploadResult) throws ParserConfigurationException, SAXException, IOException {
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase("Location")) {
                    completeMultipartUploadResult.setLocation(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    completeMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    completeMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equalsIgnoreCase("ETag")) {
                    completeMultipartUploadResult.setETag(checkChildNotNullAndGetValue(nodeItem));
                }
            }
        }
        return completeMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream inputStream, InitiateMultipartUploadResult initiateMultipartUploadResult) throws ParserConfigurationException, SAXException, IOException {
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase("UploadId")) {
                    initiateMultipartUploadResult.setUploadId(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    initiateMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    initiateMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(nodeItem));
                }
            }
        }
        return initiateMultipartUploadResult;
    }

    private static OSSObjectSummary parseObjectSummaryXML(NodeList nodeList) throws ParseException {
        OSSObjectSummary oSSObjectSummary = new OSSObjectSummary();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeItem = nodeList.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Key")) {
                    oSSObjectSummary.setKey(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("LastModified")) {
                    oSSObjectSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(nodeItem)));
                } else if (nodeName.equals("Size")) {
                    String strCheckChildNotNullAndGetValue = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue != null) {
                        oSSObjectSummary.setSize(Integer.valueOf(strCheckChildNotNullAndGetValue).intValue());
                    }
                } else if (nodeName.equals("ETag")) {
                    oSSObjectSummary.setETag(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals(URLConstant.TOKEN_PARAM_TYPE)) {
                    oSSObjectSummary.setType(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("StorageClass")) {
                    oSSObjectSummary.setStorageClass(checkChildNotNullAndGetValue(nodeItem));
                }
            }
        }
        return oSSObjectSummary;
    }

    private static String parseCommonPrefixXML(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nodeItem = nodeList.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null && nodeName.equals("Prefix")) {
                return checkChildNotNullAndGetValue(nodeItem);
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketACLResult parseGetBucketACLResponse(InputStream inputStream, GetBucketACLResult getBucketACLResult) throws ParserConfigurationException, SAXException, IOException, ParseException {
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[parseGetBucketACLResponse - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Owner")) {
                    NodeList childNodes2 = nodeItem.getChildNodes();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node nodeItem2 = childNodes2.item(i2);
                        String nodeName2 = nodeItem2.getNodeName();
                        if (nodeName2 != null) {
                            if (nodeName2.equals("ID")) {
                                getBucketACLResult.setBucketOwnerID(checkChildNotNullAndGetValue(nodeItem2));
                            } else if (nodeName2.equals("DisplayName")) {
                                getBucketACLResult.setBucketOwner(checkChildNotNullAndGetValue(nodeItem2));
                            }
                        }
                    }
                } else if (nodeName.equals("AccessControlList")) {
                    NodeList childNodes3 = nodeItem.getChildNodes();
                    for (int i3 = 0; i3 < childNodes3.getLength(); i3++) {
                        Node nodeItem3 = childNodes3.item(i3);
                        String nodeName3 = nodeItem3.getNodeName();
                        if (nodeName3 != null && nodeName3.equals("Grant")) {
                            getBucketACLResult.setBucketACL(checkChildNotNullAndGetValue(nodeItem3));
                        }
                    }
                }
            }
        }
        return getBucketACLResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListObjectsResult parseObjectListResponse(InputStream inputStream, ListObjectsResult listObjectsResult) throws ParserConfigurationException, SAXException, IOException, ParseException {
        String commonPrefixXML;
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logDebug("[parseObjectListResponse] - " + documentElement.getNodeName());
        listObjectsResult.clearCommonPrefixes();
        listObjectsResult.clearObjectSummaries();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node nodeItem = childNodes.item(i);
            String nodeName = nodeItem.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Name")) {
                    listObjectsResult.setBucketName(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("Prefix")) {
                    listObjectsResult.setPrefix(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("Marker")) {
                    listObjectsResult.setMarker(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("Delimiter")) {
                    listObjectsResult.setDelimiter(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("EncodingType")) {
                    listObjectsResult.setEncodingType(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("MaxKeys")) {
                    String strCheckChildNotNullAndGetValue = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue != null) {
                        listObjectsResult.setMaxKeys(Integer.valueOf(strCheckChildNotNullAndGetValue).intValue());
                    }
                } else if (nodeName.equals("NextMarker")) {
                    listObjectsResult.setNextMarker(checkChildNotNullAndGetValue(nodeItem));
                } else if (nodeName.equals("IsTruncated")) {
                    String strCheckChildNotNullAndGetValue2 = checkChildNotNullAndGetValue(nodeItem);
                    if (strCheckChildNotNullAndGetValue2 != null) {
                        listObjectsResult.setTruncated(Boolean.valueOf(strCheckChildNotNullAndGetValue2).booleanValue());
                    }
                } else if (nodeName.equals("Contents")) {
                    if (nodeItem.getChildNodes() != null) {
                        listObjectsResult.addObjectSummary(parseObjectSummaryXML(nodeItem.getChildNodes()));
                    }
                } else if (nodeName.equals("CommonPrefixes") && nodeItem.getChildNodes() != null && (commonPrefixXML = parseCommonPrefixXML(nodeItem.getChildNodes())) != null) {
                    listObjectsResult.addCommonPrefix(commonPrefixXML);
                }
            }
        }
        return listObjectsResult;
    }

    public static String trimQuotes(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.startsWith("\"")) {
            strTrim = strTrim.substring(1);
        }
        return strTrim.endsWith("\"") ? strTrim.substring(0, strTrim.length() - 1) : strTrim;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> map) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String str : map.keySet()) {
                if (str.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(str, map.get(str));
                } else if (str.equals("Last-Modified") || str.equals("Date")) {
                    try {
                        objectMetadata.setHeader(str, DateUtil.parseRfc822Date(map.get(str)));
                    } catch (ParseException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                } else if (str.equals("Content-Length")) {
                    objectMetadata.setHeader(str, Long.valueOf(map.get(str)));
                } else if (str.equals("ETag")) {
                    objectMetadata.setHeader(str, trimQuotes(map.get(str)));
                } else {
                    objectMetadata.setHeader(str, map.get(str));
                }
            }
            return objectMetadata;
        } catch (Exception e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public static ServiceException parseResponseErrorXML(Response response, boolean z) throws IOException {
        String strString;
        String strCheckChildNotNullAndGetValue;
        String strCheckChildNotNullAndGetValue2;
        String str;
        String str2;
        String str3;
        int iCode = response.code();
        String strHeader = response.header(OSSHeaders.OSS_HEADER_REQUEST_ID);
        String strCheckChildNotNullAndGetValue3 = null;
        if (z) {
            str3 = strHeader;
            str2 = null;
            strCheckChildNotNullAndGetValue2 = null;
            str = null;
        } else {
            try {
                strString = response.body().string();
                try {
                    NodeList childNodes = domFactory.newDocumentBuilder().parse(new InputSource(new StringReader(strString))).getDocumentElement().getChildNodes();
                    strCheckChildNotNullAndGetValue = null;
                    strCheckChildNotNullAndGetValue2 = null;
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        try {
                            Node nodeItem = childNodes.item(i);
                            String nodeName = nodeItem.getNodeName();
                            if (nodeName != null) {
                                if (nodeName.equals("Code")) {
                                    strCheckChildNotNullAndGetValue3 = checkChildNotNullAndGetValue(nodeItem);
                                }
                                if (nodeName.equals(Constants.DATABASE_TABLE_MSG)) {
                                    strCheckChildNotNullAndGetValue = checkChildNotNullAndGetValue(nodeItem);
                                }
                                if (nodeName.equals("RequestId")) {
                                    strHeader = checkChildNotNullAndGetValue(nodeItem);
                                }
                                if (nodeName.equals("HostId")) {
                                    strCheckChildNotNullAndGetValue2 = checkChildNotNullAndGetValue(nodeItem);
                                }
                            }
                        } catch (ParserConfigurationException e) {
                            e = e;
                            ThrowableExtension.printStackTrace(e);
                        } catch (SAXException e2) {
                            e = e2;
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                } catch (ParserConfigurationException e3) {
                    e = e3;
                    strCheckChildNotNullAndGetValue = null;
                    strCheckChildNotNullAndGetValue2 = strCheckChildNotNullAndGetValue;
                    ThrowableExtension.printStackTrace(e);
                    str = strString;
                    str2 = strCheckChildNotNullAndGetValue3;
                    strCheckChildNotNullAndGetValue3 = strCheckChildNotNullAndGetValue;
                    str3 = strHeader;
                    return new ServiceException(iCode, strCheckChildNotNullAndGetValue3, str2, str3, strCheckChildNotNullAndGetValue2, str);
                } catch (SAXException e4) {
                    e = e4;
                    strCheckChildNotNullAndGetValue = null;
                    strCheckChildNotNullAndGetValue2 = strCheckChildNotNullAndGetValue;
                    ThrowableExtension.printStackTrace(e);
                    str = strString;
                    str2 = strCheckChildNotNullAndGetValue3;
                    strCheckChildNotNullAndGetValue3 = strCheckChildNotNullAndGetValue;
                    str3 = strHeader;
                    return new ServiceException(iCode, strCheckChildNotNullAndGetValue3, str2, str3, strCheckChildNotNullAndGetValue2, str);
                }
            } catch (ParserConfigurationException e5) {
                e = e5;
                strString = null;
                strCheckChildNotNullAndGetValue = null;
            } catch (SAXException e6) {
                e = e6;
                strString = null;
                strCheckChildNotNullAndGetValue = null;
            }
            str = strString;
            str2 = strCheckChildNotNullAndGetValue3;
            strCheckChildNotNullAndGetValue3 = strCheckChildNotNullAndGetValue;
            str3 = strHeader;
        }
        return new ServiceException(iCode, strCheckChildNotNullAndGetValue3, str2, str3, strCheckChildNotNullAndGetValue2, str);
    }

    public static String checkChildNotNullAndGetValue(Node node) {
        if (node.getFirstChild() != null) {
            return node.getFirstChild().getNodeValue();
        }
        return null;
    }
}
