import java.io.File;
import java.io.PrintWriter;


public class synapse{

    public static void main(String[] args) {
        if(args.length != 0) {
            switch(args[0]) {
                case "create:model": 
                    System.out.println("Creating Model " + args[1]);
                    File f = new File("Model/" + args[1]);
                    try{

                        if(!f.exists()){
                            f.createNewFile();
                        }

                        PrintWriter pw = new PrintWriter(f);
                        String filename = f.getName().substring(0, f.getName().lastIndexOf("."));
                        
                        String text = "package Model" + (f.getParentFile().getName().equals("Model") ? "" : "." + f.getParentFile().getName()) + "; \n\n public class " + filename + " extends Synapse.Model {\n\n public " + filename + "() { \n\n } \n\n}";
                        pw.println(text);
                        pw.close();


                        System.out.println("Model Successfully created");
                        
                    }catch(Exception e){
                        //Catch block
                    }
                    break;
                default: 
                    break;
            }

        }
    }
}