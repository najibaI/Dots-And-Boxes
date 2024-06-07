/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
File Name:	GameOver
Programmer:	Najiba Imam
Date:		July 26, 2021
Description:	This program runs the game over screen of the game, Dots and Boxes.
*               It will display the winner, the scores of the current two players and
*               a leader board for the top five scores.

 */

package dotsandboxes;
import java.io.*;  // needed to read the text file
import java.util.*;  // this library is necessary in order to use ArrayLists
import dotsandboxes.Game;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 *
 * @author najib
 */
public class GameOver extends javax.swing.JFrame {
    public ArrayList<String> players = new ArrayList<String>();  // create the ArrayList
    
    // Declare global variables:
    String PlayerA = Game.PlayerA, PlayerB = Game.PlayerB, winner = Game.winner; 
    int ScoreA = Game.ScoreA, ScoreB = Game.ScoreB;
    String [] outList;
    /**
     * Creates new form GameOver
     */
    public GameOver() {
        initComponents();
        
        // Display winner's name:
        txtWinner.setText(winner);
        
        
        // Display Scores
        txtPlayerAScore.setText(PlayerA + ": " + ScoreA);
        txtPlayerBScore.setText(PlayerB + ": " + ScoreB);
        
        
        // Update Scores in txt file, Scores.txt
        //READING
        //Read through file and place each line into the array outList
        ArrayList <String> txtList = new ArrayList <String>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Scores.txt"));
            String word;
            while ((word = br.readLine()) != null ){
                txtList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        outList = new String[txtList.size()];
        txtList.toArray(outList);
        
        //Replace the old scores in outList with new scores
        for (int i = 0; i < outList.length; i++){
            if (outList[i].equals(PlayerA)){
                outList[i+1] = String.valueOf(ScoreA);
            }
            if (outList[i].equals(PlayerB)){
                outList[i+1] = String.valueOf(ScoreB);
            }
        }
        
        // SORT outList
        //Make a 2D array of names and scores to sort.
        String players[][] = new String [outList.length/2][2];
        int secondIndex = 0;
        for (int i = 0; i < outList.length -1; i++){
            if (i%2 == 0){ //Every other element; only names
                players[secondIndex][0] = outList[i];
                players[secondIndex][1] = outList[i+1];
                secondIndex++;
            }
        }
        
        //Sort the 2D array
        //Sort by scores
        for (int i = 0; i < players.length; i++){
            int smallestI = i; //i is initial smallest
            for (int j = i+1; j < players.length; j++){ //Everything after i
                if (Integer.parseInt(players[smallestI][1]) > Integer.parseInt(players[j][1])){ //The next smallest
                    smallestI = j;
                }
            }
            //Switch
            String tempName = players[smallestI][0];
            String tempScore = players[smallestI][1];
            players[smallestI][0] = players[i][0];
            players[smallestI][1] = players[i][1];
            players[i][0] = tempName;
            players[i][1] = tempScore;
        }
        
        //Reverse order to greatest to least
        String tempPlayers[][] = new String [players.length][2];
        for (int i = 0; i < players.length; i++){
            tempPlayers[i][0]=players[players.length-i-1][0];
            tempPlayers[i][1]=players[players.length-i-1][1];
        }
        for (int i = 0; i < players.length; i++){
            players[i][0]=tempPlayers[i][0];
            players[i][1]=tempPlayers[i][1];
        }
        
        
        
        
        //DISPLAY
        String output = "Leaderboard\n\n";
        for (int i = 0; i < players.length; i++){
            output += players[i][0] + "     " + players[i][1] + "\n";
        }
        output += "\n\n\n(Please ignore 'Reference 0')";
        txtLeaderboard.setText(output);
        
        //Update txt file to outlist
        String fileUpdate = "";
        for (int i = 0; i < outList.length; i++){
            fileUpdate += outList[i]+"\n";
        }
        
        //WRITING
        try {
            Files.write(Paths.get("Scores.txt"), fileUpdate.getBytes(), StandardOpenOption.WRITE);
        }catch (IOException e) {
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblgameOver = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLeaderboard = new javax.swing.JTextArea();
        btnExit = new javax.swing.JButton();
        lblScores = new javax.swing.JLabel();
        lblLeaderboard = new javax.swing.JLabel();
        lblWinner = new javax.swing.JLabel();
        txtPlayerBScore = new javax.swing.JTextField();
        txtWinner = new javax.swing.JTextField();
        txtPlayerAScore = new javax.swing.JTextField();
        iconStar2 = new javax.swing.JLabel();
        iconStar1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setEnabled(false);

        pnlBackground.setBackground(new java.awt.Color(94, 92, 178));

        lblgameOver.setFont(new java.awt.Font("Kristen ITC", 1, 24)); // NOI18N
        lblgameOver.setForeground(new java.awt.Color(248, 248, 248));
        lblgameOver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblgameOver.setText("GAME OVER!");

        txtLeaderboard.setEditable(false);
        txtLeaderboard.setColumns(20);
        txtLeaderboard.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        txtLeaderboard.setRows(5);
        jScrollPane1.setViewportView(txtLeaderboard);

        btnExit.setFont(new java.awt.Font("Dubai Medium", 0, 16)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblScores.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        lblScores.setForeground(new java.awt.Color(248, 248, 248));
        lblScores.setText("Total Scores");

        lblLeaderboard.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        lblLeaderboard.setForeground(new java.awt.Color(248, 248, 248));
        lblLeaderboard.setText("Leaderboard");

        lblWinner.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        lblWinner.setForeground(new java.awt.Color(248, 248, 248));
        lblWinner.setText("Winner: ");

        txtPlayerBScore.setEditable(false);
        txtPlayerBScore.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        txtPlayerBScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtWinner.setEditable(false);
        txtWinner.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtWinner.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtPlayerAScore.setEditable(false);
        txtPlayerAScore.setFont(new java.awt.Font("Ebrima", 0, 14)); // NOI18N
        txtPlayerAScore.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        iconStar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dotsandboxes/stars.jpg"))); // NOI18N

        iconStar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dotsandboxes/stars.jpg"))); // NOI18N

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconStar1)
                .addGap(33, 33, 33)
                .addComponent(lblgameOver, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(iconStar2)
                .addGap(218, 218, 218))
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPlayerAScore, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlayerBScore, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(lblWinner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScores)
                            .addComponent(txtWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(lblLeaderboard)))
                .addContainerGap(176, Short.MAX_VALUE))
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackgroundLayout.createSequentialGroup()
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackgroundLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(lblgameOver, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                                .addComponent(iconStar2)
                                .addGap(48, 48, 48))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackgroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(iconStar1)
                        .addGap(48, 48, 48)))
                .addGroup(pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWinner, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblWinner))
                .addGap(39, 39, 39)
                .addComponent(lblScores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPlayerAScore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPlayerBScore, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblLeaderboard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnExit)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0); // Exit the GUI application.
    }//GEN-LAST:event_btnExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameOver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JLabel iconStar1;
    private javax.swing.JLabel iconStar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLeaderboard;
    private javax.swing.JLabel lblScores;
    private javax.swing.JLabel lblWinner;
    private javax.swing.JLabel lblgameOver;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JTextArea txtLeaderboard;
    private javax.swing.JTextField txtPlayerAScore;
    private javax.swing.JTextField txtPlayerBScore;
    private javax.swing.JTextField txtWinner;
    // End of variables declaration//GEN-END:variables
}
