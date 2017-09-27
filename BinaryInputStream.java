import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zunwang on 2017/4/03.
 */
class BinaryInputStream extends BufferedInputStream {


    private int b;
    private int count;

    BinaryInputStream(String inPath) throws FileNotFoundException {
        super(new FileInputStream(inPath));
        b = 0;
        count = 8;
    }

    int nextBit() throws IOException {
        if (count == 8) {
            b = super.read();
            if (b == -1) {
                return -1;
            }
            count = 0;
        }
        int checker = 1 << (7 - count);
        int result = ((checker & b) == checker)? 1 : 0;
        count++;
        return result;
    }


}
