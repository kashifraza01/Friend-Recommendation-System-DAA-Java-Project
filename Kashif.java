package kashif;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Kashif extends JFrame {

    JTextArea inputwalaarea;
    JTextArea resultwalaarea;
    JTextField userfield;

    HashMap<String, HashSet<String>> graphdata;

    public Kashif() {
        graphdata = new HashMap<>();

        setTitle("Friend Recommendation System");
        setSize(650, 550);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Graph Based Friend Recommendation System",
                JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel centerpanel = new JPanel();
        centerpanel.setLayout(new GridLayout(1, 2));

        inputwalaarea = new JTextArea();
        inputwalaarea.setBorder(BorderFactory.createTitledBorder("Enter friendships (user1 user2):"));
        JScrollPane scroll1 = new JScrollPane(inputwalaarea);
        centerpanel.add(scroll1);

        resultwalaarea = new JTextArea();
        resultwalaarea.setEditable(false);
        resultwalaarea.setBorder(BorderFactory.createTitledBorder("Recommendations:"));
        JScrollPane scroll2 = new JScrollPane(resultwalaarea);
        centerpanel.add(scroll2);

        add(centerpanel, BorderLayout.CENTER);

        JPanel bottompanel = new JPanel();
        JLabel label1 = new JLabel("Enter user name:");
        bottompanel.add(label1);

        userfield = new JTextField(15);
        bottompanel.add(userfield);

        JButton checkbutton = new JButton("Recommend");
        bottompanel.add(checkbutton);

        add(bottompanel, BorderLayout.SOUTH);

        checkbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processshurukaro();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    void graphbanaya() {
        graphdata.clear();

        String rawdata = inputwalaarea.getText();
        String[] lines = rawdata.split("\n");

        for (String line : lines) {
            line = line.trim();
            if (line.length() == 0) continue;

            String[] users = line.split(" ");
            if (users.length == 2) {
                String user1 = users[0];
                String user2 = users[1];

                graphdata.putIfAbsent(user1, new HashSet<>());
                graphdata.putIfAbsent(user2, new HashSet<>());

                graphdata.get(user1).add(user2);
                graphdata.get(user2).add(user1);
            }
        }
    }

    void processshurukaro() {
        graphbanaya();
        resultwalaarea.setText("");

        String currentuser = userfield.getText().trim();

        if (!graphdata.containsKey(currentuser)) {
            resultwalaarea.setText("User not found in system!");
            return;
        }

        HashMap<String, Integer> suggestionmap = new HashMap<>();
        HashSet<String> currentuserfriends = graphdata.get(currentuser);

        for (String friend : currentuserfriends) {
            HashSet<String> friendsoffriend = graphdata.get(friend);

            for (String potentialfriend : friendsoffriend) {
                if (potentialfriend.equals(currentuser)) continue;
                if (currentuserfriends.contains(potentialfriend)) continue;

                int count = mutualfriendcount(currentuser, potentialfriend);
                suggestionmap.put(potentialfriend, count);
            }
        }

        ArrayList<String> userlist = new ArrayList<>(suggestionmap.keySet());
        for (int i = 0; i < userlist.size(); i++) {
            for (int j = i + 1; j < userlist.size(); j++) {
                String name1 = userlist.get(i);
                String name2 = userlist.get(j);

                int val1 = suggestionmap.get(name1);
                int val2 = suggestionmap.get(name2);

                if (val2 > val1) {
                    userlist.set(i, name2);
                    userlist.set(j, name1);
                }
            }
        }

        StringBuilder finaloutput = new StringBuilder();
        for (String name : userlist) {
            int score = suggestionmap.get(name);
            finaloutput.append(name)
                       .append(" -> mutual friends: ")
                       .append(score)
                       .append("\n");
        }

        if (finaloutput.toString().isEmpty()) {
            resultwalaarea.setText("No recommendations found.");
        } else {
            resultwalaarea.setText(finaloutput.toString());
        }
    }

    int mutualfriendcount(String u1, String u2) {
        HashSet<String> set1 = graphdata.get(u1);
        HashSet<String> set2 = graphdata.get(u2);
        int count = 0;

        for (String s : set1) {
            if (set2.contains(s)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        new Kashif();
    }
}
