package net.larry1123.util.config.testing;

import net.larry1123.util.config.ConfigBase;
import net.larry1123.util.config.ConfigField;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/31/14 - 9:02 AM
 */
public class TestConfigFile implements ConfigBase {

    public TestConfigFile() {
        publicByteList.add((byte) 2);
        publicByteList.add((byte) 8);
        publicByteList.add((byte) 12);
        publicByteList.add((byte) 91);
        publicByteList.add((byte) 85);
        publicDoubleList.add(58.9);
        publicDoubleList.add(5456.8);
        publicDoubleList.add(9984561.5);
        publicDoubleList.add(581.0541);
        publicDoubleList.add(58459.54985);
        publicFloatList.add((float) 891.5);
        publicFloatList.add((float) 1981.63);
        publicFloatList.add((float) 45.12);
        publicFloatList.add((float) 4581.96);
        publicFloatList.add((float) 5461.36);
    }

    // Public fields

    @ConfigField
    public boolean publicBoolean = true;
    @ConfigField
    public Boolean PublicBoolean = true;
    @ConfigField
    public byte publicByte = (byte) 51;
    @ConfigField
    public Byte PublicByte = (byte) 55;
    @ConfigField
    public byte[] publicByteArray = new byte[]{(byte) 0, (byte) 5, (byte) 6, (byte) 100, (byte) 99};
    @ConfigField
    public Byte[] PublicByteArray = new Byte[]{(byte) 1, (byte) 4, (byte) 7, (byte) 101, (byte) 98};
    @ConfigField
    public ArrayList<Byte> publicByteList = new ArrayList<Byte>();
    @ConfigField
    public char publicCharacter = (char) 52;
    @ConfigField
    public Character PublicCharacter = (char) 56;
    @ConfigField
    public double publicDouble = 519.15891D;
    @ConfigField
    public Double PublicDouble = 991.059D;
    @ConfigField
    public double[] publicDoubleArray = new double[]{45.0, 67.9, 77.2, 150.5};
    @ConfigField
    public Double[] PublicDoubleArray = new Double[]{48.6, 94.4, 991.2, 5D};
    @ConfigField
    public ArrayList<Double> publicDoubleList = new ArrayList<Double>();
    @ConfigField
    public float publicFloat = (float) 68.15;
    @ConfigField
    public Float PublicFloat = (float) 548.185;
    @ConfigField
    public float[] publicFloatArray = new float[]{(float) 541.15, (float) 941.91, (float) 905, (float) 2158};
    @ConfigField
    public Float[] PublicFloatArray = new Float[]{(float) 115.18, (float) 91165.98, (float) 8771, (float) 511};
    @ConfigField
    public ArrayList<Float> publicFloatList = new ArrayList<Float>();
    @ConfigField
    public int publicInteger = 56159612;
    @ConfigField
    public Integer PublicInteger = 4591687;
    @ConfigField
    public int[] publicIntegerArray = new int[]{};
    @ConfigField
    public Integer[] PublicIntegerArray = new Integer[]{};
    @ConfigField
    public ArrayList<Integer> publicIntegerList = new ArrayList<Integer>();
    @ConfigField
    public long publicLong = 0L;
    @ConfigField
    public Long PublicLong = 0L;
    @ConfigField
    public long[] publicLongArray = new long[0];
    @ConfigField
    public Long[] PublicLongArray = new Long[0];
    @ConfigField
    public ArrayList<Long> publicLongList = new ArrayList<Long>();
    @ConfigField
    public short publicShort = 0;
    @ConfigField
    public Short PublicShort = 0;
    @ConfigField
    public short[] publicShortArray = new short[0];
    @ConfigField
    public Short[] PublicShortArray = new Short[0];
    @ConfigField
    public ArrayList<Short> publicShortList = new ArrayList<Short>();
    @ConfigField
    public String publicString = "";
    @ConfigField
    public String[] publicStringArray = new String[0];
    @ConfigField
    public ArrayList<String> publicStringList = new ArrayList<String>();

    // Private Fields

    @ConfigField
    private boolean privateBoolean = true;
    @ConfigField
    private Boolean PrivateBoolean = true;
    @ConfigField
    private byte privateByte = 0;
    @ConfigField
    private Byte PrivateByte = 0;
    @ConfigField
    private byte[] privateByteArray = new byte[0];
    @ConfigField
    private Byte[] PrivateByteArray = new Byte[0];
    @ConfigField
    private ArrayList<Byte> PrivateByteList = new ArrayList<Byte>();
    @ConfigField
    private char privateCharacter = 0;
    @ConfigField
    private Character PrivateCharacter = 0;
    @ConfigField
    private double privateDouble = 0.0D;
    @ConfigField
    private Double PrivateDouble = 0.0D;
    @ConfigField
    private double[] privateDoubleArray = new double[0];
    @ConfigField
    private Double[] PrivateDoubleArray = new Double[0];
    @ConfigField
    private ArrayList<Double> privateDoubleList = new ArrayList<Double>();
    @ConfigField
    private float privateFloat = 0.0F;
    @ConfigField
    private Float PrivateFloat = 0.0F;
    @ConfigField
    private float[] privateFloatArray = new float[0];
    @ConfigField
    private Float[] PrivateFloatArray = new Float[0];
    @ConfigField
    private ArrayList<Float> privateFloatList = new ArrayList<Float>();
    @ConfigField
    private int privateInteger = 0;
    @ConfigField
    private Integer PrivateInteger = 0;
    @ConfigField
    private int[] privateIntegerArray = new int[0];
    @ConfigField
    private Integer[] PrivateIntegerArray = new Integer[0];
    @ConfigField
    private ArrayList<Integer> privateIntegerList = new ArrayList<Integer>();
    @ConfigField
    private long privateLong = 0L;
    @ConfigField
    private Long PrivateLong = 0L;
    @ConfigField
    private long[] privateLongArray = new long[0];
    @ConfigField
    private Long[] PrivateLongArray = new Long[0];
    @ConfigField
    private ArrayList<Long> privateLongList = new ArrayList<Long>();
    @ConfigField
    private short privateShort = 0;
    @ConfigField
    private Short PrivateShort = 0;
    @ConfigField
    private short[] privateShortArray = new short[0];
    @ConfigField
    private Short[] PrivateShortArray = new Short[0];
    @ConfigField
    private ArrayList<Short> privateShortList = new ArrayList<Short>();
    @ConfigField
    private String privateString = "";
    @ConfigField
    private String[] privateStringArray = new String[0];
    @ConfigField
    private ArrayList<String> privateStringList = new ArrayList<String>();

}
