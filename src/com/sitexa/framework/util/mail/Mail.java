package com.sitexa.framework.util.mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class Mail {

    String to = "";//收件人
    String from = "";//发件人
    String host = "";//smtp主机
    String username = "";
    String password = "";
    String filename = "";//附件文件名
    String subject = "";//邮件主题
    String content = "";//邮件正文
    Vector file = new Vector();//附件文件集合

    public Mail() {
    }

    public Mail(String to, String from, String smtpServer, String username, String password, String subject, String content) {
        this.to = to;
        this.from = from;
        this.host = smtpServer;
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Vector getFile() {
        return file;
    }

    public String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(), "UTF-8"), "UTF-8", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }

    @SuppressWarnings("unchecked")
    public void attachfile(String fname) {
        file.addElement(fname);
    }

    public boolean sendMail() {

        //构造mail session
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            //构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            subject = transferChinese(subject);
            msg.setSubject(subject);

            //构造Multipart
            Multipart mp = new MimeMultipart();

            //向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setText(content);
            //向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            //向Multipart添加附件
            Enumeration efile = file.elements();
            while (efile.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
                filename = efile.nextElement().toString();
                FileDataSource fds = new FileDataSource(filename);
                mbpFile.setDataHandler(new DataHandler(fds));
                mbpFile.setFileName(fds.getName());
                //向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            file.removeAllElements();
            //向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            //发送邮件
            Transport.send(msg);

        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Mail sendmail = new Mail();
        sendmail.setHost("smtp.gmail.com");
        sendmail.setUsername("sitexa");
        sendmail.setPassword("tswcbyy8");
        sendmail.setTo("xnpeng@163.com");
        sendmail.setFrom("sitexa@gmail.com");
        sendmail.setSubject("你好，这是测试！");
        sendmail.setContent("你好这是一个带多附件的测试！");
        sendmail.sendMail();

    }
}