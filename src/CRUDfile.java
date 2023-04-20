import java.io.*;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.String;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class CRUD<T> {
    private  String Path = "students.txt";
    private ArrayList<T> objects = new ArrayList<>();
    public CRUD(String Path){
        this.Path = Path;
        load();
    }

    public void add(Object student) {
        objects.add((T)student);
        save();
    }

    public void remove(Object student) {
        objects.remove((T)student);
        save();
    }

    public void update(Object student) {
        int index = objects.indexOf(student);
        if (index != -1) {
            objects.set(index, (T)student);
            save();
        }
    }

    public static <T extends IntroduceMyself> int getIdF(T stud3ent, String path) {
        String csv = stud3ent.toCSV();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int ind = 0;
            while ((line = reader.readLine()) != null) {
                if(line.equals(csv)){
                    return ind;
                }
                ind++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public ArrayList<T> search(Predicate<T> predicate) {
        ArrayList<T> result = new ArrayList<>();
        for (T student : objects) {
            if (predicate.test(student)) {
                result.add(student);
            }
        }
        return result;
    }

    public ArrayList<T> getAll() {
        return objects;
    }

    private void save(){
        Field[] fields = objects.size() == 0 ? new Field[0]: objects.get(0).getClass().getDeclaredFields();
        try (PrintWriter writer = new PrintWriter(new FileWriter(Path))) {
            String Fields = "", Types = "";
            for (Field f: fields){
                Fields += f.getName() + ",";
                Types += f.getType().getName() + ",";
            }
            writer.println(objects.get(0).getClass().getName());
            writer.println(Fields.substring(0, Fields.length() - 1));
            writer.println(Types.substring(0, Types.length() - 1));
            for (T obj : objects) {
                writer.println(objectToCSV(obj));
                save_nested_objects(obj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void save_nested_objects(T object){
        File file = new File(Path);
        String parentPath = file.getParent();
        Field Fs[] = object.getClass().getDeclaredFields();
        for(Field f:Fs){
            if(f.getType().getName().split(".").length == 1){
                CRUD<?> nested = new CRUD<>(parentPath + "/" + object.getClass().getName() + ".txt");
                if(nested.search(e -> e.hashCode() == object.hashCode()).size()==0){
                    nested.add(object);
                }
            }
        }
    }
    public static <T> String objectToCSV(T object) {
        StringBuilder csv = new StringBuilder();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            csv.append(value != null ? value.toString() : "");
            csv.append(",");
        }
        csv.setLength(csv.length() - 1);
        return csv.toString();
    }


    protected  void load() {
        objects.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(Path))) {
            String line = reader.readLine();
            String className = line;
            String[] colums = reader.readLine().split(",");
            String types = reader.readLine();
            while ((line = reader.readLine()) != null) {
                objects.add((T) csvToObject(line,types,Class.forName(className)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static  Constructor<?>  constructor(String className, String types) throws Exception {
        Class<?> clazz = Class.forName(className);
        String[] paramTypes = types.split(",");
        Class<?>[] paramClasses = new Class[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            paramClasses[i] = Class.forName(paramTypes[i]);
        }
        Constructor<?> constructor = clazz.getConstructor(paramClasses);
        return constructor;
    }
    public <T> T csvToObject(String csv, String dataTypeString, Class<T> clazz) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        String[] values = csv.split(",");
        String[] types = dataTypeString.split(",");
        T object = clazz.getDeclaredConstructor().newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = i < values.length ? convertValue(types[i],values[i]) : "";
            fields[i].set(object, value);
        }
        return object;
    }
    public Object convertValue(String type, String value) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (type.split(".").length == 3) {
            if (type.split(".")[1].equals("util")) {
                String[] elems = value.substring(1, value.length() - 1).split(",");
                String ttt = elems[0].split("@")[0];
                List<?> l = Arrays.stream(elems).map(e -> {
                    try {
                        return convertValue(ttt,e);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchMethodException ex) {
                        throw new RuntimeException(ex);
                    } catch (InvocationTargetException ex) {
                        throw new RuntimeException(ex);
                    } catch (InstantiationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList());
                Constructor<?> c  = Class.forName(type).getConstructor(Collection.class);
                return c.newInstance(l);
            }
        }

        switch (type) {
            case "int":
                return Integer.parseInt(value);
            case "double":
                return Double.parseDouble(value);
            case "float":
                return Float.parseFloat(value);
            case "long":
                return Long.parseLong(value);
            case "boolean":
                return Boolean.parseBoolean(value);
            case "char":
                return value.charAt(0);
            case "byte":
                return Byte.parseByte(value);
            case "short":
                return Short.parseShort(value);
            case "java.lang.Integer":
                return Integer.valueOf(value);
            case "java.lang.Double":
                return Double.valueOf(value);
            case "java.lang.Float":
                return Float.valueOf(value);
            case "java.lang.Long":
                return Long.valueOf(value);
            case "java.lang.Boolean":
                return Boolean.valueOf(value);
            case "java.lang.Character":
                return Character.valueOf(value.charAt(0));
            case "java.lang.Byte":
                return Byte.valueOf(value);
            case "java.lang.Short":
                return Short.valueOf(value);
            case "java.lang.String":
                return value;
            case "java.util.Date":
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            default:
                File file = new File(Path);
                File Folder = new File(file.getParent());
                if(Folder.isDirectory()){
                    String f[] = Folder.list();
                    for(String file1:f){
                        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
                            String line = reader.readLine();
                            if(line.equals(type))
                                return (new CRUD<>(file1)).search(e -> e.toString() == value).get(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return value;
        }
    }
}
