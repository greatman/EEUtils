package net.larry1123.util.config.testing;

import net.larry1123.util.config.ConfigBase;
import net.larry1123.util.config.ConfigField;

import java.util.ArrayList;

/**
 * @author Larry1123
 * @since 1/31/14 - 9:02 AM
 */
@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "MismatchedReadAndWriteOfArray"})
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
        publicIntegerList.add(984);
        publicIntegerList.add(87458);
        publicIntegerList.add(-841);
        publicIntegerList.add(-9120000);
        publicIntegerList.add(4981651);
        publicLongList.add(9511L);
        publicLongList.add(915L);
        publicLongList.add(-98511L);
        publicLongList.add(-110L);
        publicLongList.add(54100L);
        publicShortList.add((short) 198);
        publicShortList.add((short) 9125);
        publicShortList.add((short) 9815);
        publicShortList.add((short) -1585);
        publicShortList.add((short) -548);
        publicStringList.add("Test String 6");
        publicStringList.add("Test String 7");
        publicStringList.add("Test String 8");
        publicStringList.add("Test String 9");
        publicStringList.add("Test String 10");

        privateByteList.add((byte) 845);
        privateByteList.add((byte) 945);
        privateByteList.add((byte) -5641);
        privateByteList.add((byte) 515);
        privateByteList.add((byte) 942);
        privateDoubleList.add(-548.5);
        privateDoubleList.add(548.02);
        privateDoubleList.add(895D);
        privateDoubleList.add(-8574.057);
        privateDoubleList.add(-1854D);
        privateFloatList.add(-515F);
        privateFloatList.add(548.05F);
        privateFloatList.add(4585.05F);
        privateFloatList.add(591.05F);
        privateFloatList.add(891.05F);
        privateFloatList.add(918.0F);
        privateIntegerList.add(-518);
        privateIntegerList.add(1820);
        privateIntegerList.add(-1200);
        privateIntegerList.add(94124);
        privateIntegerList.add(91550);
        privateLongList.add(912L);
        privateLongList.add(-942L);
        privateLongList.add(-945158L);
        privateLongList.add(51265L);
        privateLongList.add(515L);
        privateShortList.add((short) -125);
        privateShortList.add((short) 54120);
        privateShortList.add((short) 995);
        privateShortList.add((short) -942);
        privateShortList.add((short) 1058);
        privateStringList.add("Test String 16");
        privateStringList.add("Test String 17");
        privateStringList.add("Test String 18");
        privateStringList.add("Test String 19");
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
    public int[] publicIntegerArray = new int[]{195, 19878, 187110, -894, 8941};
    @ConfigField
    public Integer[] PublicIntegerArray = new Integer[]{9811, -8100, 98743, 548, -9};
    @ConfigField
    public ArrayList<Integer> publicIntegerList = new ArrayList<Integer>();
    @ConfigField
    public long publicLong = 56919L;
    @ConfigField
    public Long PublicLong = 189588L;
    @ConfigField
    public long[] publicLongArray = new long[]{818L, 4851L, 95195, -5481L, 9115L};
    @ConfigField
    public Long[] PublicLongArray = new Long[]{8511L, 8441L, 987465L, -8711L, -874L};
    @ConfigField
    public ArrayList<Long> publicLongList = new ArrayList<Long>();
    @ConfigField
    public short publicShort = 5961;
    @ConfigField
    public Short PublicShort = 9145;
    @ConfigField
    public short[] publicShortArray = new short[]{81, -47, 95, 648, 188};
    @ConfigField
    public Short[] PublicShortArray = new Short[]{184, 648, 187, 328, -874};
    @ConfigField
    public ArrayList<Short> publicShortList = new ArrayList<Short>();
    @ConfigField
    public String publicString = "Test String 1";
    @ConfigField
    public String[] publicStringArray = new String[]{"Test String 2", "Test String 3", "Test String 4", "Test String 5"};
    @ConfigField
    public ArrayList<String> publicStringList = new ArrayList<String>();

    // Private Fields

    @ConfigField
    private boolean privateBoolean = true;
    @ConfigField
    private Boolean PrivateBoolean = true;
    @ConfigField
    private byte privateByte = (byte) 549;
    @ConfigField
    private Byte PrivateByte = (byte) 984;
    @ConfigField
    private byte[] privateByteArray = new byte[]{(byte) 981, (byte) 854, (byte) -586, (byte) 91};
    @ConfigField
    private Byte[] PrivateByteArray = new Byte[]{(byte) 549, (byte) 871, (byte) -987, (byte) 4156};
    @ConfigField
    private ArrayList<Byte> privateByteList = new ArrayList<Byte>();
    @ConfigField
    private char privateCharacter = 9654;
    @ConfigField
    private Character PrivateCharacter = 4598;
    @ConfigField
    private double privateDouble = 5781.63;
    @ConfigField
    private Double PrivateDouble = 9845.15;
    @ConfigField
    private double[] privateDoubleArray = new double[]{-5618.2, 54951.91, -5496.054, 4584};
    @ConfigField
    private Double[] PrivateDoubleArray = new Double[]{-54185.18, 85491.5, 4185D, -198.2};
    @ConfigField
    private ArrayList<Double> privateDoubleList = new ArrayList<Double>();
    @ConfigField
    private float privateFloat = 8542.85F;
    @ConfigField
    private Float PrivateFloat = -845.2F;
    @ConfigField
    private float[] privateFloatArray = new float[]{-155F, 545.0F, 8542.2F, 6912.5F};
    @ConfigField
    private Float[] PrivateFloatArray = new Float[]{-546.5F, 58455F, -515F, 54850.56F};
    @ConfigField
    private ArrayList<Float> privateFloatList = new ArrayList<Float>();
    @ConfigField
    private int privateInteger = -8545;
    @ConfigField
    private Integer PrivateInteger = 5491;
    @ConfigField
    private int[] privateIntegerArray = new int[]{84, 854, 910, 485, -515};
    @ConfigField
    private Integer[] PrivateIntegerArray = new Integer[]{941, -842, 548, -9155, 5481};
    @ConfigField
    private ArrayList<Integer> privateIntegerList = new ArrayList<Integer>();
    @ConfigField
    private long privateLong = 910L;
    @ConfigField
    private Long PrivateLong = 880L;
    @ConfigField
    private long[] privateLongArray = new long[]{981L, -91L, 584L, 66L};
    @ConfigField
    private Long[] PrivateLongArray = new Long[]{9125L, 5110L, -912L, 645L};
    @ConfigField
    private ArrayList<Long> privateLongList = new ArrayList<Long>();
    @ConfigField
    private short privateShort = 510;
    @ConfigField
    private Short PrivateShort = -915;
    @ConfigField
    private short[] privateShortArray = new short[]{915, -851, 658, 28, -74};
    @ConfigField
    private Short[] PrivateShortArray = new Short[]{815, -850, 912, 315, -912};
    @ConfigField
    private ArrayList<Short> privateShortList = new ArrayList<Short>();
    @ConfigField
    private String privateString = "Test String 11";
    @ConfigField
    private String[] privateStringArray = new String[]{"Test String 12", "Test String 13", "Test String 14"};
    @ConfigField
    private ArrayList<String> privateStringList = new ArrayList<String>();

}
