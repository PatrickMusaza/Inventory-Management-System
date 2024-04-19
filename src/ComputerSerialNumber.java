
import java.lang.management.ManagementFactory;

public class ComputerSerialNumber {

    public static void main(String[] args) {
        String serialNumber = getMotherboardSerialNumber();
        System.out.println("Computer Serial Number: " + serialNumber);
    }

    public static String getMotherboardSerialNumber() {
        String serialNumber = "Unknown";
        try {
            String osName = System.getProperty("os.name");
            if (osName.startsWith("Windows")) {
                // For Windows
                serialNumber = WindowsSerialNumberRetriever.getSerialNumber();
            } else if (osName.startsWith("Linux")) {
                // For Linux
                serialNumber = LinuxSerialNumberRetriever.getSerialNumber();
            } else if (osName.startsWith("Mac")) {
                // For macOS
                serialNumber = MacSerialNumberRetriever.getSerialNumber();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serialNumber;
    }

    static class WindowsSerialNumberRetriever {
        public static String getSerialNumber() throws Exception {
            String serial = null;
            // Run the WMIC command to get serial number
            Process process = Runtime.getRuntime().exec(new String[] {"wmic", "bios", "get", "serialnumber"});
            process.getOutputStream().close();
            try (java.util.Scanner sc = new java.util.Scanner(process.getInputStream())) {
                while (sc.hasNext()) {
                    String next = sc.next();
                    if ("SerialNumber".equals(next)) {
                        serial = sc.next().trim();
                        break;
                    }
                }
            }
            return serial != null ? serial : "Unknown";
        }
    }

    static class LinuxSerialNumberRetriever {
        public static String getSerialNumber() throws Exception {
            String serial = null;
            // Run the command to get serial number
            Process process = Runtime.getRuntime().exec(new String[] {"bash", "-c", "sudo dmidecode -s baseboard-serial-number"});
            process.getOutputStream().close();
            try (java.util.Scanner sc = new java.util.Scanner(process.getInputStream())) {
                if (sc.hasNext()) {
                    serial = sc.next().trim();
                }
            }
            return serial != null ? serial : "Unknown";
        }
    }

    static class MacSerialNumberRetriever {
        public static String getSerialNumber() throws Exception {
            String serial = null;
            // Run the command to get serial number
            Process process = Runtime.getRuntime().exec(new String[] {"bash", "-c", "system_profiler SPHardwareDataType | awk '/Serial/ {print $4}'"});
            process.getOutputStream().close();
            try (java.util.Scanner sc = new java.util.Scanner(process.getInputStream())) {
                if (sc.hasNext()) {
                    serial = sc.next().trim();
                }
            }
            return serial != null ? serial : "Unknown";
        }
    }
}
