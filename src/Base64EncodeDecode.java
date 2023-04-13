/*
Java 8 has finally added Base64 capabilities to the standard API, via the java.util.Base64 utility class.
*/


import java.util.Base64;
import java.util.UUID;

public class Base64EncodeDecode {

    public static void main(String[] args) {
        System.out.println("Base644 Encode Decode");

       /* The basic encoder keeps things simple and encodes the input as-is, without any line separation.
        The encoder maps the input to a set of characters in the A-Za-z0-9+/ character set.*/

        String originalInput = "test input";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println("Encoded String");
        System.out.println(encodedString);

        //Decoding
        System.out.println();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        System.out.println("Decoded String");
        System.out.println(decodedString);

        /*Java 8 Base64 Encoding Without Padding
        In Base64 encoding, the length of an output-encoded String must be a multiple of three.
        The encoder adds one or two padding characters (=) at the end of the output as needed in order to meet this requirement.

        Upon decoding, the decoder discards these extra padding characters.
        Sometimes, we need to skip the padding of the output. For instance, the resulting String will never be decoded back.
        So, we can simply choose to encode without padding:*/

        System.out.println("Java 8 Base64 Encoding Without Padding");

        byte[] encodedByte = Base64.getEncoder().withoutPadding().encode(originalInput.getBytes());
        String encodedStringWithoutPadding = new String(encodedByte);
        System.out.println(encodedStringWithoutPadding);

        System.out.println("Trying to decode");

        byte[] decodedByte = Base64.getDecoder().decode(encodedStringWithoutPadding.getBytes());
        System.out.println(new String(decodedByte));

        System.out.println("Java 8 URL Encoding");

        /*URL Encoding - Encodes special characters in the URL so they may be properly understood by the browser when requesting a web page.
        Special characters might include space, plus, percent sign etc.. URL Decoding - Decodes URL encoded values back to their original value.
        */

        String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java";
        System.out.println("Orginal URL");
        System.out.println(originalUrl);
        String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes());
        System.out.println("Encoded URL");
        System.out.println(encodedUrl);

        byte[] decodedUrlBytes = Base64.getUrlDecoder().decode(encodedUrl);
        String decodedUrl = new String(decodedUrlBytes);
        System.out.println("Decoded URL");
        System.out.println(decodedUrl);

        System.out.println("Java 8 MIME Encoding ");

        /*MIME - Multipurpose Internet Mail Extensions (MIME) is an Internet standard that extends the format of email messages to support
        text in character sets other than ASCII, as well as attachments of audio, video, images, and application programs.*/

        /*The MIME encoder generates a Base64-encoded output using the basic alphabet. However, the format is MIME-friendly.
        Each line of the output is no longer than 76 characters. Also, it ends with a carriage return followed by a linefeed (\r\n):
        */

        StringBuilder buffer = getMimeBuffer();
        byte[] encodedAsBytes = buffer.toString().getBytes();
        System.out.println("Encoded Input");
        System.out.println(new String(encodedAsBytes));
        String encodedMime = Base64.getMimeEncoder().encodeToString(encodedAsBytes);

        System.out.println("Encoded MIME");
        System.out.println(encodedMime);


        byte[] decodedMIMEBytes = Base64.getMimeDecoder().decode(encodedMime);
        String decodedMime = new String(decodedMIMEBytes);
        System.out.println("Decoded MIME");
        System.out.println(decodedMime);

       /* Encoding/Decoding Using Apache Commons Code

       The Apache Commons Codec package contains simple encoder and decoders for various formats such as Base64 and Hexadecimal.
       In addition to these widely used encoders and decoders, the codec package also maintains a collection of phonetic encoding utilities
       *
       *The main API is the org.apache.commons.codec.binary.Base64 class. We can initialize it with various constructors:
       * Base64(boolean urlSafe) creates the Base64 API by controlling the URL-safe mode (on or off).
       * Base64(int lineLength) creates the Base64 API in a URL-unsafe mode and controls the length of the line (default is 76).
       * Base64(int lineLength, byte[] lineSeparator) creates the Base64 API by accepting an extra line separator, which by default is CRLF (“\r\n”).
       * Once the Base64 API is created, both encoding and decoding are quite simple:
        String originalInput = "test input";
        Base64 base64 = new Base64();
        String encodedString = new String(base64.encode(originalInput.getBytes()));
       *
       * */

      /*  Benchmarks
        Performance for encoding 10K characters for different JDKs. java.util.Base64 is a clear winner performing almost 10x faster.

        https://dkomanov.medium.com/base64-encoding-performance-jdk-vs-apache-commons-3fb83323414b

        */




    }

    private static StringBuilder getMimeBuffer() {
        StringBuilder buffer = new StringBuilder();
        for (int count = 0; count < 10; ++count) {
            buffer.append(UUID.randomUUID().toString());
        }
        return buffer;
    }
}
