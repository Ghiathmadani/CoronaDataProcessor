package com.workatplanon.data_processor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import models.Meeting;
import models.Person;
import parser.MeetingsParser;
import parser.PersonsParser;

public class Meeting_Data_Processor {
private static String path1;
private static String path2;

public static void main(String[] args) {
	try (Scanner reader = new Scanner(System.in)) {
		logo();
		int auswahl;
		System.out.println(
				"Please Enter (1) for Case A" + " or (2) for Case B or" + " (3) for Case C " + "or (4) for exit :");
		try {
			while (true) {
			auswahl = reader.nextInt();
				switch (auswahl) {
				case 1:
					path1 = "src\\resource\\CaseA_Input_Persons.csv";
					path2 = "src\\resource\\CaseA_Input_Meetings.csv";
					diseased_person(path1, path2);
					break;
				case 2:
					path1 = "src\\resource\\CaseB_Input_Persons.csv";
					path2 = "src\\resource\\CaseB_Input_Meetings.csv";
					diseased_person(path1, path2);
					break;
				case 3:
					path1 = "src\\resource\\CaseC_Input_Persons.csv";
					path2 = "src\\resource\\CaseC_Input_Meetings.csv";
					diseased_person(path1, path2);
					break;
				case 4:
					System.out.println("bye bye");
					System.exit(1);
					break;
				default:
					System.out.println("Invalid input");
					continue;
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("You have made an invalid input");
		} catch (Exception e) {
			System.exit(1);
			System.out.println("You have made something invalid (Andere Exception)");
			e.printStackTrace();
		}
	}
}

private static void diseased_person(String path1, String path2) throws IOException {
	PersonsParser personsParser = new PersonsParser();
	MeetingsParser meetingsParser = new MeetingsParser();

	List<Person> persons = personsParser.getPersons(path1);
	List<Person> infectedPersons = new ArrayList<Person>();
	for (Person person : persons) {
		if (!person.getLastPositiveCheck().equals(LocalDateTime.of(0000, 11, 11, 11, 11))) {
			infectedPersons.add(person);
		}
	}
	List<List<Integer>> directList = new ArrayList<>();
	List<List<Integer>> indirectList = new ArrayList<>();
	List<Meeting> meetings1 = meetingsParser.getMeetings(path2);
	List<Meeting> meetings2 = meetingsParser.getMeetings(path2);

	System.out.println("Infection Report");
	for (Person infectedPerson : infectedPersons) {
		System.out.println("===============================================");
		System.out.println("Infected Person number: " + infectedPerson.getId());
		System.out.println("-------------------------------------------");
		System.out.println("Contacted Persons in the last seven days:");
		System.out.println("-------------------------------------------");

		for (Meeting meetingA : meetings1) {
			if (meetingA.getStartDate().isAfter(infectedPerson.getLastPositiveCheck())
					&& meetingA.getEndDate().isBefore(infectedPerson.getLastPositiveCheck().plusDays(7))) {
				Set<Integer> participantsIds = new HashSet<>(meetingA.getParticipantIds());
				if (participantsIds.contains(infectedPerson.getId())) {
					participantsIds.remove(infectedPerson.getId());
					System.out.println(participantsIds.toString());
					directList.add(participantsIds.stream().collect(Collectors.toList()));
					Set<Person> indirectPersons = new HashSet<>();
					for (Integer indirectPersonID : participantsIds) {
						indirectPersons.add(new Person(indirectPersonID, meetingA.getEndDate()));
					}
//					System.out.println("Indirect Contacted Persons in the last seven days:");
//					System.out.println("-------------------------------------------");
//					for (Person indirectPerson : indirectPersons) {
//						for (Meeting meetingB : meetings2) {
//							if (meetingB.getStartDate().isAfter(indirectPerson.getLastPositiveCheck()) && meetingB
//								.getEndDate().isBefore(indirectPerson.getLastPositiveCheck().plusDays(7))) {
//							Set<Integer> indirectParticipantsIds = new HashSet<>(meetingB.getParticipantIds());
//							if (indirectParticipantsIds.contains(indirectPerson.getId())) {
//								indirectParticipantsIds.remove(indirectPerson.getId());
//								System.out.println(indirectParticipantsIds.toString());
//								indirectList.add(indirectParticipantsIds.stream().collect(Collectors.toList()));
//							}
//						}
//					}
//				}
				}
			}

		}
	}
	System.out.println("----------------------------------------------------------");
	System.out.println("Infected Persons in a direct way");
	System.out.println(listCombine(directList));
	System.out.println("----------------------------------------------------------");
	System.out.println("Infected Persons in an indirect way");
	System.out.println(listCombine(indirectList));
	System.out.println("================================================================================");
	System.out.println("\n Please Enter (1) for Case A" + " or (2) for Case B or(3) for Case C or (4) for exit :");
}

private static Set<Integer> listCombine(List<List<Integer>> Lists) {
	return Lists.stream().flatMap(List::stream).collect(Collectors.toSet());

}

private static void logo() {
	System.out.println("\r\n"
			+ "___  ___          _   _              ______      _         ______                                      \r\n"
			+ "|  \\/  |         | | (_)             |  _  \\    | |        | ___ \\                                     \r\n"
			+ "| .  . | ___  ___| |_ _ _ __   __ _  | | | |__ _| |_ __ _  | |_/ / __ ___   ___ ___  ___ ___  ___ _ __ \r\n"
			+ "| |\\/| |/ _ \\/ _ \\ __| | '_ \\ / _` | | | | / _` | __/ _` | |  __/ '__/ _ \\ / __/ _ \\/ __/ __|/ _ \\ '__|\r\n"
			+ "| |  | |  __/  __/ |_| | | | | (_| | | |/ / (_| | || (_| | | |  | | | (_) | (_|  __/\\__ \\__ \\  __/ |   \r\n"
			+ "\\_|  |_/\\___|\\___|\\__|_|_| |_|\\__, | |___/ \\__,_|\\__\\__,_| \\_|  |_|  \\___/ \\___\\___||___/___/\\___|_|   \r\n"
			+ "                               __/ |                                                                   \r\n"
			+ "                              |___/                                                                    \r\n"
			+ "");

}
}
