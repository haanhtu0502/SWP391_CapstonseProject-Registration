/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Groups;
import DTO.LectureTopic;
import DTO.Semester;
import DTO.StudentGroup;
import DTO.Topic;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HLong
 */
public class TestConnection {

    public static void main(String[] args) {
//        test student readAll()

//        ArrayList<Student> list=StudentDAO.readAll();
//        for (Student student : list) {
//            System.out.println(student.getStudentId()+","+student.getName()+","+student.getPassword()+","+student.getStatus()+","+student.getEmail()+","+student.getDepartmentId());
//        } 
//******************************************
//        test student create()
//        Student student = new Student(7,"Ha Anh Tu31","12345678123",1,"haanhtu@g123mail.com",2);
//        StudentDAO.create(student);
//        System.out.println(student.getStudentId()+","+student.getName()+","+student.getPassword()+","+student.getStatus()+","+student.getEmail()+","+student.getDepartmentId());
//******************************************
//        test student read()
//        Student student = StudentDAO.read(3);
//        System.out.println(student.getStudentId()+","+student.getName()+","+student.getPassword()+","+student.getStatus()+","+student.getEmail()+","+student.getDepartmentId());
//******************************************
//        test student update()
//        Student student = new Student(3,"Ha Anh Tu1","12345678123",0,"haanhtu@g123mail.com",2);
//        StudentDAO.update(student);
//******************************************
//        test student delete()
//        StudentDAO.delete(7);
//******************************************
//        test studentGroup readAll()
//        ArrayList<StudentGroup> list = StudentGroupDAO.readAll();
//
//        for (StudentGroup student : list) {
//            System.out.println(student.getId() + "," + student.getStudentId()+ "," + student.getGroupId()+ "," + student.getLeaderStatus());
//        }
//******************************************
//        test studentGroup read()
//        StudentGroup student = StudentGroupDAO.read(3);
//        System.out.println(student.getId() + "," + student.getStudentId()+ "," + student.getGroupId()+ "," + student.getLeaderStatus());
//******************************************
//        test studentGroup create()
//          Student student6 = new Student(6,"Nguyen Van A","12345678123",1,"nguyenvana@g123mail.com",2);
//          StudentDAO.create(student6);
//          StudentGroup studentGroup = new StudentGroup(6,6,1,0);
//          StudentGroupDAO.create(studentGroup);
//          ArrayList<StudentGroup> list = StudentGroupDAO.readAll();
//
//          for (StudentGroup student : list) {
//            System.out.println(student.getId() + "," + student.getStudentId()+ "," + student.getGroupId()+ "," + student.getLeaderStatus());
//          }
//******************************************
//          test studentGroup update()
//          StudentGroup studentGroup = new StudentGroup(3,5,1,0);
//          StudentGroupDAO.update(studentGroup);
//******************************************
//          test studentGroup delete()
//          StudentGroupDAO.delete(6);
//          ArrayList<StudentGroup> list = StudentGroupDAO.readAll();
//
//          for (StudentGroup student : list) {
//            System.out.println(student.getId() + "," + student.getStudentId()+ "," + student.getGroupId()+ "," + student.getLeaderStatus());
//          }
//******************************************
//          test Topic readAll()
//          ArrayList<Topic> list = TopicDAO.readAll();
//
//          for (Topic topic : list) {
//            System.out.println(topic.getTopicId()+ "," + topic.getName()+ "," + topic.getCatergoryId()+ "," + topic.getDescription()+ "," + topic.getBusinessId()+ "," + topic.getDepartmentId());
//          }
//******************************************
//          test Topic read()
//          Topic topic = TopicDAO.read(2);
//          System.out.println(topic.getTopicId()+ "," + topic.getName()+ "," + topic.getCatergoryId()+ "," + topic.getDescription()+ "," + topic.getBusinessId()+ "," + topic.getDepartmentId());
//******************************************
//          test Topic create()
//          Topic topic1 = new Topic(5,"design",10,"beautyfull design",5,2);
//          TopicDAO.create(topic1);
//          ArrayList<Topic> list = TopicDAO.readAll();
//
//          for (Topic topic : list) {
//            System.out.println(topic.getTopicId()+ "," + topic.getName()+ "," + topic.getCatergoryId()+ "," + topic.getDescription()+ "," + topic.getBusinessId()+ "," + topic.getDepartmentId());
//          }
//******************************************
//          test Topic update()
//          Topic topic1 = new Topic(5,"Testing",10,"beautyfull design",5,2);
//          TopicDAO.update(topic1);
//          ArrayList<Topic> list = TopicDAO.readAll();
//
//          for (Topic topic : list) {
//            System.out.println(topic.getTopicId()+ "," + topic.getName()+ "," + topic.getCatergoryId()+ "," + topic.getDescription()+ "," + topic.getBusinessId()+ "," + topic.getDepartmentId());
//          }
//******************************************
//          test Topic delete()
//          TopicDAO.delete(5);
//          ArrayList<Topic> list = TopicDAO.readAll();
//          for (Topic topic : list) {
//            System.out.println(topic.getTopicId()+ "," + topic.getName()+ "," + topic.getCatergoryId()+ "," + topic.getDescription()+ "," + topic.getBusinessId()+ "," + topic.getDepartmentId());
//          }
//******************************************
//          test Semester readAll()
//          ArrayList<Semester> list = SemesterDAO.readAll();
//
//          for (Semester sem : list) {
//            System.out.println(sem.getSemesterId()+ "," + sem.getName()+ "," + sem.getStartDate()+ "," + sem.getEndDate()+ "," + sem.getTopicId());
//          }
//******************************************
//          test Semester read()
//          Semester sem = SemesterDAO.read(2);
//          System.out.println(sem.getSemesterId()+ "," + sem.getName()+ "," + sem.getStartDate()+ "," + sem.getEndDate()+ "," + sem.getTopicId());
//******************************************
//          test Semester create()
//          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//          Date startDate=null;
//          Date endDate=null;
//        try {
//             startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-10");
//             endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-06-12");
//        } catch (ParseException ex) {
//            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          Semester sem1 = new Semester(5,"SW2022",startDate,endDate,4);
//          SemesterDAO.create(sem1);
//          ArrayList<Semester> list = SemesterDAO.readAll();
//          for (Semester sem : list) {
//            System.out.println(sem.getSemesterId()+ "," + sem.getName()+ "," + sem.getStartDate()+ "," + sem.getEndDate()+ "," + sem.getTopicId());
//          }
//******************************************
//          test Semester update()
//          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//          Date startDate=null;
//          Date endDate=null;
//        try {
//             startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-02-11");
//             endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-07-12");
//        } catch (ParseException ex) {
//            Logger.getLogger(TestConnection.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          Semester sem1 = new Semester(5,"SW2021",startDate,endDate,3);
//          SemesterDAO.update(sem1);
//          ArrayList<Semester> list = SemesterDAO.readAll();
//          for (Semester sem : list) {
//            System.out.println(sem.getSemesterId()+ "," + sem.getName()+ "," + sem.getStartDate()+ "," + sem.getEndDate()+ "," + sem.getTopicId());
//          }
//******************************************
//          test Semester delete()
//          SemesterDAO.delete(5);
//          ArrayList<Semester> list = SemesterDAO.readAll();
//          for (Semester sem : list) {
//            System.out.println(sem.getSemesterId()+ "," + sem.getName()+ "," + sem.getStartDate()+ "," + sem.getEndDate()+ "," + sem.getTopicId());
//          }
//*******************************************
//           test Groups readAll()
//           ArrayList<Groups> list = GroupsDAO.readAll();
//           for (Groups group : list) {
//              System.out.println(group.getGroupId() + "," + group.getGroupName()+ "," + group.getSemId());
//           }
//******************************************
//        test Groups create()
//        Groups groups = new Groups(5,"Group 5",4);
//        GroupsDAO.create(groups);
//        System.out.println(groups.getGroupId()+","+groups.getGroupName()+","+groups.getSemId());
//******************************************
//        test Groups read()
//        Groups groups = GroupsDAO.read(3);
//        System.out.println(groups.getGroupId()+","+groups.getGroupName()+","+groups.getSemId());
//******************************************
//        test Groups update()
//        Groups groups = new Groups(2,"Worst group",2);
//        GroupsDAO.update(groups);
//******************************************
//        test Groups delete()
//        GroupsDAO.delete(5);
//******************************************
//           test LectureTopic readAll()
           ArrayList<LectureTopic> list = LectureTopicDAO.readAll();
           for (LectureTopic lectureTopic : list) {
              System.out.println(lectureTopic.getId()+ "," + lectureTopic.getLectureId()+ "," + lectureTopic.getTopicId());
           }
//******************************************
//        test LectureTopic create()
//        LectureTopic lectureTopic = new LectureTopic(3,1,1);
//        LectureTopicDAO.create(lectureTopic);
//        System.out.println(lectureTopic.getId()+","+lectureTopic.getLectureId()+","+lectureTopic.getTopicId());
//******************************************
//        test LectureTopic read()
//        LectureTopic lectureTopic = LectureTopicDAO.read(1);
//        System.out.println(lectureTopic.getId()+","+lectureTopic.getLectureId()+","+lectureTopic.getTopicId());
//******************************************
//        test LectureTopic update()
//        LectureTopic lectureTopic = new LectureTopic(2,1,2);
//        LectureTopicDAO.update(lectureTopic);
//******************************************
//        test LectureTopic delete()
//        LectureTopicDAO.delete(2);
//******************************************
    }
}
