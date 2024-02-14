import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Main {

    public static String pathOtdel = "C://JavaCode//Pract_Zimn1//Отделы/";

    public static String path1 = "";
    public static Font FFont = new Font("Arial", Font.PLAIN, 24);

    public static String[] Otdeli = {};

    public static String[] Sotrudniki = {};

    public static DefaultListModel modelForOtdel = new DefaultListModel();
    public static DefaultListModel modelForSotrudnik = new DefaultListModel();

    static void Scan(){

        File folder = new File(pathOtdel);
        List<String> lines = new ArrayList<String>();
        for (File file : folder.listFiles())
        {
            lines.add(file.getName());

        }
        Otdeli = lines.toArray(new String[0]);
    }

    static void Scan1(String path){
        File folder = new File(path);
        List<String> lines = new ArrayList<String>();
        for (File file : folder.listFiles())
        {
            String name = file.getName();
            int Namelength = file.getName().length();
            lines.add(name.substring(0,Namelength-5));

        }
        Sotrudniki = lines.toArray(new String[0]);
    }

    // Метод с новым графическим окном с добалением/изменением отдела
    public static void addOtd(String title, String name, String btn, JList<String> list, JLabel kkol) {
        JFrame fds = new JFrame(title);
        final JLabel otd = new JLabel();
        otd.setBounds(30, 0, 360, 50);
        otd.setForeground(Color.RED);
        otd.setText(name);
        otd.setFont(FFont);
        fds.add(otd);

        final JTextField txt = new JTextField();
        txt.setBounds(30, 45, 290, 25);
        txt.setFont(FFont);
        fds.add(txt);

        fds.setLayout(null);
        fds.setPreferredSize(new Dimension(500, 200));
        fds.setVisible(true);
        fds.pack();

        JButton cxz = new JButton(btn);
        cxz.setBounds(30, 80, 140, 30);
        fds.add(cxz);

        JButton bvc = new JButton("Отмена");
        bvc.setBounds(180, 80, 140, 30);
        fds.add(bvc);

        bvc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fds.dispose();
            }
        });

        cxz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btn == "Добавить"){

                    String save = pathOtdel + txt.getText();
                    File D = new File(save);
                    D.mkdir();

                    fds.dispose();
                    Scan();

                    DefaultListModel model = new DefaultListModel();
                    for (int i = 0; i < Otdeli.length; i++) {
                        model.addElement(Otdeli[i]);
                    }
                    list.setModel(model);
                    String kolvo = Integer.toString(new File(pathOtdel).listFiles().length);
                    kkol.setText(kolvo);
                    fds.dispose();


                }

                if (btn == "Изменить") {

                    String asd = list.getSelectedValue();
                    String save = pathOtdel + asd;
                    File file = new File(save);
                    String save1 = pathOtdel + txt.getText();
                    File newFile = new File(save1);
                    file.renameTo(newFile);

                    Scan();

                    DefaultListModel model = new DefaultListModel();
                    for (int i = 0; i < Otdeli.length; i++) {
                        model.addElement(Otdeli[i]);
                    }

                    list.setModel(model);

                    fds.dispose();

                }

            }
        });

    }
    //_____________________________________

    // Метод с новым графическим окном с добалением/изменением сотрудников
    public static void addSotr(String title, String btn, JList<String> list, JLabel kkol, String save) {
        JFrame fds = new JFrame(title);
        final JLabel sotr = new JLabel();
        sotr.setBounds(30, 0, 360, 50);
        sotr.setForeground(Color.RED);
        sotr.setText("Введите ФИО");
        sotr.setFont(FFont);
        fds.add(sotr);

        final JTextField fio = new JTextField();
        fio.setBounds(30, 45, 290, 25);
        fio.setFont(FFont);
        fds.add(fio);

        final JLabel age = new JLabel();
        age.setBounds(30, 65, 360, 50);
        age.setForeground(Color.RED);
        age.setText("Введите возраст");
        age.setFont(FFont);
        fds.add(age);

        final JTextField agetxt = new JTextField();
        agetxt.setBounds(240, 80, 80, 25);
        agetxt.setFont(FFont);
        fds.add(agetxt);

        final JLabel ZP = new JLabel();
        ZP.setBounds(30, 100, 360, 50);
        ZP.setForeground(Color.RED);
        ZP.setText("Введите ЗП");
        ZP.setFont(FFont);
        fds.add(ZP);

        final JTextField ZPtxt = new JTextField();
        ZPtxt.setBounds(180, 115, 140, 25);
        ZPtxt.setFont(FFont);
        fds.add(ZPtxt);

        JButton add = new JButton(btn);
        add.setBounds(30, 150, 140, 30);
        fds.add(add);

        JButton bvc = new JButton("Отмена");
        bvc.setBounds(180, 150, 140, 30);
        fds.add(bvc);

        fds.setLayout(null);
        fds.setPreferredSize(new Dimension(370, 250));
        fds.setVisible(true);
        fds.pack();

        bvc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fds.dispose();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (btn == "Добавить"){

                    try {
                        Integer test = new Integer(agetxt.getText());
                        test = new Integer(ZPtxt.getText());
                        String path = save+"//"+fio.getText()+".json";

                        File D = new File(path);

                        try {
                            D.createNewFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        try(FileWriter writer = new FileWriter(path, true))
                        {

                            String text = agetxt.getText() + "\n" + ZPtxt.getText();
                            writer.write(text);
                            writer.flush();
                        }
                        catch(IOException ex){
                        }

                        fds.dispose();
                        Scan1(save);

                        DefaultListModel model1 = new DefaultListModel();
                        for (int i = 0; i < Sotrudniki.length; i++) {
                            model1.addElement(Sotrudniki[i]);
                        }
                        list.setModel(model1);
                        String kolvo = Integer.toString(new File(save).listFiles().length);
                        kkol.setText(kolvo);

                        fds.dispose();

                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(fds, "Некорректно введен возраст или зарплата","Ошибка", JOptionPane.ERROR_MESSAGE);
                    }


                }

                if (btn == "Изменить") {

                    try {

                        Integer test = new Integer(agetxt.getText());
                        test = new Integer(ZPtxt.getText());

                        String asd = list.getSelectedValue() + ".json";
                        String save = path1 + "/" + asd;

                        File file = new File(save);
                        file.delete();
                        String path = path1 + "/" + fio.getText()+".json";

                        File D = new File(path);

                        try {
                            D.createNewFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        try(FileWriter writer = new FileWriter(path, true))
                        {

                            String text = agetxt.getText() + "\n" + ZPtxt.getText();
                            writer.write(text);
                            writer.flush();
                        }
                        catch(IOException ex){
                        }

                        Scan1(path1);


                        DefaultListModel model1 = new DefaultListModel();
                        for (int i = 0; i < Sotrudniki.length; i++) {
                            model1.addElement(Sotrudniki[i]);
                        }
                        list.setModel(model1);


                        fds.dispose();

                    } catch (NumberFormatException x) {
                        JOptionPane.showMessageDialog(fds, "Некорректно введен возраст или зарплата","Ошибка", JOptionPane.ERROR_MESSAGE);
                    }


                }

            }
        });

    }
    //__________________________________________________________

    public static void Sum(JList<String> list, JLabel sumZP){
        String age = "";
        String zp = "";
        int sum = 0;

        String asd = list.getSelectedValue();
        String path2 = pathOtdel + asd;

        File folder = new File(path2);

        String kolvo = Integer.toString(new File(path2).listFiles().length);

        for (File file : folder.listFiles())
        {
            try {
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                age = reader.readLine();
                zp = reader.readLine();

                Integer ZPint = new Integer(zp);
                sum = sum + ZPint;
                reader.close();


            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }


        }
        String sum1 = Integer.toString(sum);

        sumZP.setText(sum1 + " рублей");
    }

    public static void main(String[] args) throws IOException {


        JFrame fr = new JFrame("Предприятие");

        // Модуль с отделами
        final JLabel otd = new JLabel();
        otd.setBounds(30, 0, 160, 50);
        otd.setForeground(Color.RED);
        otd.setText("Отделы");
        otd.setFont(FFont);
        fr.add(otd);

        JButton add = new JButton("Добавить");
        add.setBounds(200, 15, 110, 30);
        fr.add(add);
        JButton del = new JButton("Удалить");
        del.setBounds(200, 50, 110, 30);
        fr.add(del);
        JButton edit = new JButton("Редактировать");
        edit.setBounds(30, 50, 165, 30);
        fr.add(edit);
        JButton sel = new JButton("Выбрать");
        sel.setBounds(30, 85, 280, 30);
        fr.add(sel);

        Scan();

        for (int i = 0; i < Otdeli.length; i++) {
            modelForOtdel.addElement(Otdeli[i]);
        }
        final JList<String> list = new JList(modelForOtdel);
        list.setBounds(20, 120, 300, 500);
        fr.add(list);
        JScrollPane sp = new JScrollPane(list);
        sp.setBounds(20, 120, 300, 500);
        fr.add(sp);

        final JLabel kol = new JLabel();
        kol.setBounds(30, 620, 200, 50);
        kol.setForeground(Color.RED);
        kol.setText("Кол-во отделов:");
        kol.setFont(FFont);
        fr.add(kol);

        final JLabel kkol = new JLabel();
        kkol.setBounds(230, 620, 200, 50);
        String kolvo = Integer.toString(new File(pathOtdel).listFiles().length);
        kkol.setText(kolvo);
        kkol.setFont(FFont);
        fr.add(kkol);
        //________________________________________________

        // Добавление отдела
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addOtd("Добавить отдел", "Введите название отдела", "Добавить", list, kkol);

            }

        });

        //_________________________

        // Удаление отдела
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Scan();

                String asd = list.getSelectedValue();

                if (asd != null){

                    String save = pathOtdel + asd;
                    File D = new File(save);
                    if (D.delete()){}
                    else{
                        for (File file : D.listFiles())
                        {
                            file.delete();
                        }
                        D.delete();
                        modelForSotrudnik.clear();
                    }

                    Scan();

                    modelForOtdel.clear();

                    for (int i = 0; i < Otdeli.length; i++) {
                        modelForOtdel.addElement(Otdeli[i]);
                    }

                    list.setModel(modelForOtdel);
                    String kolvo = Integer.toString(new File(pathOtdel).listFiles().length);
                    kkol.setText(kolvo);

                }
                else{
                    JOptionPane.showMessageDialog(fr, "Вы не выбрали отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        //_________________________

        // Изменение отдела
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (list.getSelectedValue() != null){
                    addOtd("Изменение отдела", "Введите новое название отдела", "Изменить", list, kkol);
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Вы не выбрали отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }



            }

        });
        //_________________

        // Модуль с сотрудниками
        final JLabel sotr = new JLabel();
        sotr.setBounds(350, 0, 160, 50);
        sotr.setForeground(Color.RED);
        sotr.setText("Сотрудники");
        sotr.setFont(FFont);
        fr.add(sotr);

        JButton add1 = new JButton("Добавить");
        add1.setBounds(520, 15, 110, 30);
        fr.add(add1);
        JButton del1 = new JButton("Удалить");
        del1.setBounds(520, 50, 110, 30);
        fr.add(del1);
        JButton edit1 = new JButton("Редактировать");
        edit1.setBounds(350, 50, 165, 30);
        fr.add(edit1);
        JButton sel1 = new JButton("Выбрать");
        sel1.setBounds(350, 85, 280, 30);
        fr.add(sel1);

        final JList<String> list1 = new JList(Sotrudniki);
        list1.setBounds(340, 120, 300, 500);
        fr.add(list1);
        JScrollPane sp1 = new JScrollPane(list1);
        sp1.setBounds(340, 120, 300, 500);
        fr.add(sp1);

        final JLabel kkol1 = new JLabel();
        kkol1.setBounds(600, 620, 200, 50);
        kkol1.setText("");
        kkol1.setFont(FFont);
        fr.add(kkol1);

        final JLabel sumZP1 = new JLabel();
        sumZP1.setBounds(670, 520, 400, 30);
        sumZP1.setText("_________________");
        sumZP1.setFont(FFont);
        fr.add(sumZP1);

        sel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String asd = list.getSelectedValue();
                if (asd != null){

                    Sum(list, sumZP1);

                    modelForSotrudnik.clear();

                    path1 = pathOtdel + asd;
                    Scan1(path1);

                    for (int i = 0; i < Sotrudniki.length; i++) {
                        modelForSotrudnik.addElement(Sotrudniki[i]);
                    }
                    list1.setModel(modelForSotrudnik);

                    String kolvo1 = Integer.toString(new File(path1).listFiles().length);
                    kkol1.setText(kolvo1);

                }
                else{
                    JOptionPane.showMessageDialog(fr, "Вы не выбрали отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        // Добавление сотрудника
        add1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (path1 != ""){
                    addSotr("Добавить сотрудника", "Добавить", list1, kkol1, path1);
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Сначала выберите отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //__________________________________________

        // Удаление сотрудника

        del1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (path1 != ""){

                    Scan1(path1);

                    String asd = list1.getSelectedValue();

                    if (asd != null){

                        asd = list1.getSelectedValue() + ".json";
                        String save = path1 + "/" + asd;
                        File D = new File(save);
                        D.delete();

                        Scan1(path1);

                        modelForSotrudnik.clear();

                        for (int i = 0; i < Sotrudniki.length; i++) {
                            modelForSotrudnik.addElement(Sotrudniki[i]);
                        }

                        list1.setModel(modelForSotrudnik);
                        String kolvo = Integer.toString(new File(path1).listFiles().length);
                        kkol1.setText(kolvo);

                    }
                    else{
                        JOptionPane.showMessageDialog(fr, "Вы не выбрали сотрудника", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }

                }
                else{
                    JOptionPane.showMessageDialog(fr, "Сначала выберите отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        //___________________________

        // Изменение сотрудника
        edit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (path1 != ""){
                    if (list1.getSelectedValue() != null){
                        addSotr("Изменить сотрудника", "Изменить", list1, kkol1, path1);
                    }
                    else{
                        JOptionPane.showMessageDialog(fr, "Вы не выбрали сотрудника", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(fr, "Сначала выберите отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //___________________________________

        final JLabel kol1 = new JLabel();
        kol1.setBounds(350, 620, 240, 50);
        kol1.setForeground(Color.RED);
        kol1.setText("Кол-во сотрудников:");
        kol1.setFont(FFont);
        fr.add(kol1);
        //__________________________________________________

        // Модуль с информацией о сотруднике
        final JLabel inf = new JLabel();
        inf.setBounds(670, 0, 320, 50);
        inf.setForeground(Color.RED);
        inf.setText("Информация о сотруднике");
        inf.setFont(FFont);
        fr.add(inf);

        final JLabel FIO = new JLabel();
        FIO.setBounds(670, 50, 300, 30);
        FIO.setText("ФИО:");
        FIO.setFont(FFont);
        fr.add(FIO);

        final JLabel FIO1 = new JLabel();
        FIO1.setBounds(740, 50, 500, 30);
        FIO1.setText("______________");
        FIO1.setFont(FFont);
        fr.add(FIO1);

        final JLabel Age = new JLabel();
        Age.setBounds(670, 85, 120, 30);
        Age.setText("Возраст:");
        Age.setFont(FFont);
        fr.add(Age);

        final JLabel Age1 = new JLabel();
        Age1.setBounds(775, 85, 250, 30);
        Age1.setText("____");
        Age1.setFont(FFont);
        fr.add(Age1);

        final JLabel ZP = new JLabel();
        ZP.setBounds(670, 120, 120, 30);
        ZP.setText("Зарплата:");
        ZP.setFont(FFont);
        fr.add(ZP);

        final JLabel ZP1 = new JLabel();
        ZP1.setBounds(790, 120, 200, 30);
        ZP1.setText("__________");
        ZP1.setFont(FFont);
        fr.add(ZP1);

        sel1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (list.getSelectedValue() != null){

                    if (list1.getSelectedValue() != null){

                        String age = "";
                        String zp = "";

                        String name = list1.getSelectedValue() + ".json";
                        String path = path1 + "/" + name;

                        FIO1.setText(list1.getSelectedValue());

                        File file = new File(path);
                        try {
                            FileReader fr = new FileReader(file);
                            BufferedReader reader = new BufferedReader(fr);
                            age = reader.readLine();
                            zp = reader.readLine();
                            reader.close();

                        } catch (FileNotFoundException ex) {
                        } catch (IOException ex) {
                        }

                        Age1.setText(age);
                        ZP1.setText(zp + " рублей");
                    }
                    else{
                        JOptionPane.showMessageDialog(fr, "Вы не выбрали сотрудника", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }

                }

                else{
                    JOptionPane.showMessageDialog(fr, "Сначала выберите отдел", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }


            }
        });

        //__________________________________________________

        // Модуль с суммой ЗП в отделе
        final JLabel sumZP = new JLabel();
        sumZP.setBounds(670, 485, 400, 30);
        sumZP.setForeground(Color.RED);
        sumZP.setText("Сумма ЗП в выбранном отделе");
        sumZP.setFont(FFont);
        fr.add(sumZP);



        // Ненужная? кнопка
        JButton btnsum = new JButton("Посчитать");
        btnsum.setBounds(910, 520, 110, 30);
        fr.add(btnsum);

        btnsum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Sum(list, sumZP1);

            }
        });
        //

        //____________________________________________

        fr.setLayout(null);
        fr.setPreferredSize(new Dimension(1280, 720));
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();

    }
}