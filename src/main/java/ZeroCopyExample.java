import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class ZeroCopyExample
{
    
    public static void main(String[] args)
    {
        ZeroCopyExample channel = new ZeroCopyExample();
        try
        {
            if(args.length < 3)
            {
                System.out.println("usage: ZeroCopyExample <source> <destination> <mode>\n");
                return;
            }
            long s = System.currentTimeMillis();
            if("1".equals(args[2]))
            {
                channel.copy(args[0], args[1]);
            }
            else
            {
                channel.zeroCopy(args[0], args[1]);
            }
            long e = System.currentTimeMillis();
            System.out.println("Total time in millis " + (e-s));
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void zeroCopy(String from, String to) throws IOException
    {
        
        FileChannel source = null;
        FileChannel destination = null;
        try
        {
            source = new FileInputStream(from).getChannel();
            destination = new FileOutputStream(to).getChannel();
            source.transferTo(0, source.size(), destination);
        }
        finally
        {
            if(source != null)
            {
                source.close();
            }
            if(destination != null)
            {
                destination.close();
            }
            
        }
    }
    
    public void copy(String from, String to) throws IOException
    {
        
        byte[] data = new byte[8*1024];
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long bytesToCopy = new File(from).length();
        long bytesCopied = 0;
        try
        {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            
            while(bytesCopied < bytesToCopy)
            {
                fis.read(data);
                fos.write(data);
                bytesCopied += data.length;
            }
            fos.flush();
        }
        finally
        {
            if(fis != null)
            {
                fis.close();
            }
            if(fos != null)
            {
                fos.close();
            }
        }
        
    }
    
}