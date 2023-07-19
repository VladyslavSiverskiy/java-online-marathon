public static String readFile(String filename) {
    try(FileInputStream fis = new FileInputStream(filename)){
        StringBuilder stringBuilder = new StringBuilder();
        byte[] buffer = new byte[7]; // Adjust the buffer size as per your needs
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            String tempStr = "0";
            for (int i = 0; i < bytesRead; i++) {
                tempStr += (char) buffer[i];
                if(i % 6 == 0 && i != 0){
                    byte[] byteArray = new byte[1];
                    byteArray[0] = (byte) Integer.parseInt(tempStr, 2);
                    stringBuilder.append(new String(byteArray));                        tempStr = "";
                }
            }
        }
        return stringBuilder.toString();
    }catch (IOException e){
        e.printStackTrace();
        return "";
    }
}
