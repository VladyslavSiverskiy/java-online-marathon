public static void writeFile(String filename, String text) {
    try(FileOutputStream fos = new FileOutputStream(filename)){
        StringBuilder result = new StringBuilder();
        for(char c: text.toCharArray()){
            String binaryString = Integer.toBinaryString(c);
            if(binaryString.length() < 7){
                String zeros = "";
                for (int i = 0; i < 7 - binaryString.length(); i++){
                    zeros+="0";
                }
                binaryString = zeros + binaryString;
            }
            result.append(binaryString);
        }
        byte[] bytes = result.toString().getBytes();
        fos.write(bytes);
    }catch (IOException e){
        e.printStackTrace();
    }
}
