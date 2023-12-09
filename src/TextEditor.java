import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menu;
    JTextArea textArea;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile,cut,copy,paste,selectAll,close;

    TextEditor(){
        //initialising the functionalities of menu bar
        newFile=new JMenuItem("New File");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //adding Action Listeners to cut, copy, paste....etc
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //initialising the menu bar items
        file=new JMenu("File");
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit=new JMenu("Edit");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //initialising the menu bar
        menu=new JMenuBar();
        menu.add(file);
        menu.add(edit);

        textArea=new JTextArea();

        //creating a panel for layout of textarea
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);
//        JScrollPane scroll=new JScrollPane(textArea,30,20);
//        panel.add(scroll);

        //creating the frame
        frame=new JFrame();

        frame.setJMenuBar(menu);
        frame.add(panel);

        frame.setBounds(0,0,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut)
        {
            textArea.cut();
        }
        if(actionEvent.getSource()==copy)
        {
            textArea.copy();
        }
        if(actionEvent.getSource()==paste)
        {
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close)
        {
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //getting the selected file
                File file=fileChooser.getSelectedFile();
                //getting the path of selected file
                String filePath=file.getPath();
                try{
                    //initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //initialise the buffer reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);

                    //reading the selected file
                    String intermediate="";
                    StringBuilder output=new StringBuilder();
                    while((intermediate=bufferedReader.readLine())!=null)
                        output.append(intermediate+'\n');

                    //setting the textarea with the output we received
                    textArea.setText(output.toString());
                }catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile)
        {
            JFileChooser fileChooser=new JFileChooser();
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter=new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile)
        {
            TextEditor textEditor=new TextEditor();
        }
    }
    public static void main(String[] args) {
       TextEditor textEditor=new TextEditor();
    }
}