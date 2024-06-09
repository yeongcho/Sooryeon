import java.util.InputMismatchException; //InputMismatchException 클래스를 사용하기 위한 임포트문
import java.util.Scanner; // 입력 보조 클래스
import java.util.Calendar; //Calendar 클래스를 사용하기 위한 임포트문
import java.util.GregorianCalendar; //GregorianCalendar 클래스를 사용하기 위한 임포트문

public class ScheduleManagementProgram {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		ScheduleManagement manager = new ScheduleManagement(100); // 일정표 매니저 생성

		//test용
		Schedule addOne = new Schedule("2004", "10", "25", "내생일", "상", "10", "00", "20", "00", "취미","케이크 사오기"); //test용
		manager.addSchedule(addOne);//test용
		Schedule addTwo = new Schedule("1996", "02", "06", "오빠생일", "중", "12", "00", "20", "00", "기타","선물 사오기"); //test용
		manager.addSchedule(addTwo);//test용
		Schedule addThree = new Schedule("1969", "12", "05", "엄마생일", "상", "10", "00", "20", "00", "기타","케이크 사오기"); //test용
		manager.addSchedule(addThree);
		//test용

		while (true) { // 반복하기
			// 일정표의 기능들을 보여주기
			System.out.println("");
			System.out.println("-----------------------*********일정표 프로그램*********------------------------");
			System.out.println("1. 일정 등록");
			System.out.println("2. 일정 검색 및 일정 삭제");
			System.out.println("3. 일정 전체 출력");
			System.out.println("4. 종료");
			System.out.println("---------------------------------------------------------------------------");
			System.out.print("원하시는 기능의 번호를 입력해주세요 : ");

			int scheduleMenu = 0; //메뉴선택을 위한 변수

			while (true) {
				try {
					scheduleMenu = scan.nextInt(); // 번호 입력 받기
					scan.nextLine(); // 버퍼 비우기
					break;
				} catch (InputMismatchException e) {
					scan.nextLine(); // 잘못된 입력을 비우기
					System.out.println("잘못된 입력입니다. 다시 입력하세요.");
				}
			}

			switch (scheduleMenu) {
			case 1: // 일정 등록
				System.out.println("'1. 일정 등록'을 선택하셨습니다.");	//선택한 메뉴 안내하기 
				while (true) {
					// 일정표에 입력할 내용 안내하기
					System.out.println("");
					System.out.println("---------------------*********일정표에 입력할 내용*********----------------------");
					System.out.println("1. 날짜 (ex) 2004/07/26)");
					System.out.println("2. 일정 제목");
					System.out.println("3. 일정의 중요도 (상 또는 중 또는 하 를 선택하여 입력해주세요.)");
					System.out.println("4. 일정의 종류 (학업, 운동, 회의, 취미, 기타 중 하나를 선택해서 입력해주세요.)");
					System.out.println("5. 시작 시간 (ex) 01:00) (0에서 24시간 사이로 입력해주세요.)");
					System.out.println("6. 종료 시간 (ex) 01:00) (0에서 24시간 사이로 입력해주세요.)");
					System.out.println("7. 메모할 내용");
					System.out.println("----------------------------------------------------------------------------");

					//일정 내용을 담기 위한 변수들
					String year = null, title = null, importance = null, kinds = null, memo = null ,endTimeInput = null ,startTimeInput = null, startTimeHour = null, startTimeMinute = null, endTimeHour = null, endTimeMinute = null, month = null, day = null, isDuplicateAnswer = null;
					String[] startInputTimeArray = null, endInputTimeArray = null, date = null;
					boolean validInput = false;
					while (!validInput) {
						try {
							System.out.print("일정을 입력할 날짜 : ");	//일정 날짜 입력받기
							date = scan.nextLine().split("/"); // 년, 월과 일 쪼개기
							year = date[0]; //년
							month = date[1]; // 월
							day = date[2]; // 일

							System.out.print("일정 제목 : "); //일정 제목 입력받기 
							title = scan.nextLine();

							System.out.print("일정의 중요도 : ");	//일정 중요도 입력받기
							importance = scan.nextLine();

							System.out.print("일정의 종류 : ");	//일정 종류 입력받기
							kinds = scan.nextLine();

							System.out.print("시작 시간 : ");	//시작 시간 입력받기
							startTimeInput = scan.nextLine(); 
							if (startTimeInput.contains(":")) { // ":"가 포함되어 있는지 확인
								startInputTimeArray = startTimeInput.split(":"); // 시와 분을 쪼개기
								startTimeHour = startInputTimeArray[0]; // 시
								startTimeMinute = startInputTimeArray[1]; // 분
							} else {	// ":"가 포함되어 있지 않으면
								startTimeHour = startTimeInput;	// 그대로 시로 사용
								System.out.println("분 을 입력해주세요. : ");
								startTimeMinute = scan.nextLine(); // 분을 따로 입력 받기
							}

							System.out.print("종료 시간 : ");	//종료 시간 입력받기
							endTimeInput = scan.nextLine();
							if (endTimeInput.contains(":")) { // ":"가 포함되어 있는지 확인
								endInputTimeArray = endTimeInput.split(":"); // 시와 분을 쪼개기
								endTimeHour = endInputTimeArray[0]; // 시
								endTimeMinute = endInputTimeArray[1]; // 분
							} else {	// ":"가 포함되어 있지 않으면
								endTimeHour = endTimeInput;	// 그대로 시로 사용
								System.out.println("분 을 입력해주세요. : ");
								endTimeMinute = scan.nextLine(); // 분을 따로 입력 받기
							}

							System.out.print("메모할 내용 : "); //메모 입력받기
							memo = scan.nextLine();
							validInput = true;
						}
						catch (java.lang.ArrayIndexOutOfBoundsException e) {
							System.out.println("날짜를 형식에 맞게 다시 입력해주세요.");
						}
					}

					try {

						Schedule mySchedule = new Schedule(year, month, day, title, importance, startTimeHour, startTimeMinute, endTimeHour, endTimeMinute, kinds, memo); // 일정의 내용 등록

						if (manager.isDuplicate(mySchedule)) {
							System.out.println("같은 일정이 이미 추가되어 있습니다."); //같은 일정이 이미 있다고 안내하기
							while(true) {
								System.out.println("이대로 추가하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //이대로 추가할 것인지 물어보기
								isDuplicateAnswer = scan.nextLine();
								
								if (isDuplicateAnswer.equals("예")) {
									manager.addSchedule(mySchedule); //일정 추가
									break;
								} 
								else if((isDuplicateAnswer.equals("아니요"))) {
									System.out.println("일정 추가가 취소되었습니다.");
									break;
								}
								else {
									System.out.println("다시 입력해주세요.");
								}
							}
						} else {
							manager.addSchedule(mySchedule);//일정 추가
						}
						break;
					} catch (NumberFormatException e) {
						scan.nextLine(); // 잘못된 입력을 비우기
						System.out.println("일정 등록에 실패했습니다.");
						System.out.println("날짜와 시간을 숫자로 입력해주세요.");
					}
				}
				break;
			case 2: //일정 검색 및 삭제
				System.out.println("'2. 일정 검색 및 삭제'을 선택하셨습니다.");	//선택한 메뉴 안내하기 
				// 일정 검색의 기능들을 보여주기
				System.out.println("");
				System.out.println("-----------------------*********일정표 프로그램*********------------------------");
				System.out.println("1. 해당 날짜 이후의 일정들 검색");
				System.out.println("2. 해당 날짜들 사이에 있는 일정들 검색");
				System.out.println("3. 해당 단어가 일정 제목 또는 일정 내용에 포함된 일정들 검색");
				System.out.println("4. 해당 우선순위인 일정들 검색");
				System.out.println("5. 해당 카테고리의 일정들 검색 (학업, 운동, 회의, 취미, 기타 에서 선택해주세요.)");
				System.out.println("---------------------------------------------------------------------------");
				System.out.print("원하시는 기능의 번호를 입력해주세요 : ");

				int searchMenu = 0; //메뉴선택을 위한 변수

				while (true) {
					try {
						searchMenu = scan.nextInt(); // 번호 입력 받기
						scan.nextLine(); // 버퍼 비우기
						break;
					} catch (InputMismatchException e) {
						scan.nextLine(); // 잘못된 입력을 비우기
						System.out.println("잘못된 입력입니다. 다시 입력하세요.");
					}
				}
				switch (searchMenu) {
				case 1: // 해당 날짜 이후의 일정들 검색
					try {
						String searchDateInput = null, deleteAnswer = null;
						int searchYear = 0, searchMonth = 0, searchDay = 0;

						GregorianCalendar searchDate = new GregorianCalendar();
						
						boolean validInput = false;
						while (!validInput) {
							try {
								System.out.println("날짜를 입력해주세요. (ex) 2024월 02월 03일)(년,월,일 or 년,월 or 년)");
								searchDateInput = scan.nextLine().replaceAll(" ", "");	// 문자열에서 모든 띄어쓰기를 제거

								int yearIndex = searchDateInput.indexOf("년");// "년" 문자열의 인덱스 찾기
								searchYear = Integer.parseInt(searchDateInput.substring(0, yearIndex));// "년" 문자열 이전의 문자열을 정수로 변환

								if (yearIndex + 1 < searchDateInput.length()) {// "년" 문자열 뒤에 다른 문자가 있는지 확인
									int monthIndex = searchDateInput.indexOf("월", yearIndex);// "월" 문자열의 인덱스를 찾기
									searchMonth = Integer.parseInt(searchDateInput.substring(yearIndex + 1, monthIndex));// "년"과 "월" 사이의 부분 문자열을 정수로 변환

									if (monthIndex + 1 < searchDateInput.length()) {// "월" 문자열 뒤에 다른 문자가 있는지 확인
										int dayIndex = searchDateInput.indexOf("일", monthIndex);//"일" 문자열의 인덱스를 찾습니다.
										searchDay = Integer.parseInt(searchDateInput.substring(monthIndex + 1, dayIndex));// "월"과 "일" 사이의 부분 문자열을 정수로 변환
									}
								}

								if (searchMonth == 0 && searchDay == 0) {	//년만 입력한 경우의 값넣기
									searchDate.set(Calendar.YEAR, searchYear);
									searchDate.set(Calendar.MONTH, searchMonth);
									searchDate.set(Calendar.DATE, searchDay);
								}
								else if (searchDay == 0) { //년, 월만 입력한 경우의 값넣기
									searchDate.set(Calendar.YEAR, searchYear);
									searchDate.set(Calendar.MONTH, searchMonth - 1);
									searchDate.set(Calendar.DATE, searchDay);
								}
								else {	//모두 입력한 경우의 값넣기
									searchDate.set(Calendar.YEAR, searchYear);
									searchDate.set(Calendar.MONTH, searchMonth - 1 );
									searchDate.set(Calendar.DATE, searchDay - 1);
								}
								validInput = true;
							} catch (java.lang.StringIndexOutOfBoundsException e) {
								System.out.println("년,월,일 or 년,월 or 년 이라는 단어들을 사용해서 입력해주세요.");
							}
						}

						Schedule[] dateSchedules = manager.search(searchDate);
						int[] indexes = manager.searchIndexes(searchDate);
						
						if (indexes.length > 0) { //검색결과 출력하기
							System.out.println("검색 결과:");
							for (int i = 0; i < indexes.length; i++) {
								System.out.println(dateSchedules[indexes[i]].getYear() + "년 " + dateSchedules[indexes[i]].getMonth() + "월 " + dateSchedules[indexes[i]].getDay() + "일 : ");
								System.out.println("    일정 제목 : " + dateSchedules[indexes[i]].getTitle() + " (" + dateSchedules[indexes[i]].getImportance() + "/" + dateSchedules[indexes[i]].getKinds() + ")");
								System.out.println("    시간 : " + dateSchedules[indexes[i]].getStartTimeHour() + " : " + dateSchedules[indexes[i]].getStartTimeMinute() + " ~ " + dateSchedules[indexes[i]].getEndTimeHour() + " : " + dateSchedules[indexes[i]].getEndTimeMinute());
								System.out.println("    메모 : " + dateSchedules[indexes[i]].getMemo());
								System.out.println("--");
							}
						} else {
							System.out.println("검색 결과가 없습니다.");
							break;
						}
						while(true) {
							System.out.println("일정을 삭제하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //일정 삭제할 지 묻기
							deleteAnswer = scan.nextLine();

							if (deleteAnswer.equals("예")) {
								System.out.println("몇 번째 일정을 삭제하시겠습니까? (1부터 시작하며 숫자로 대답해주세요.)(ex) 1)"); // 삭제할 번호 물어보기
							    int deleteIndex = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리하기

							    if (deleteIndex >= 1 && deleteIndex <= dateSchedules.length) {
							        Schedule toDelete = dateSchedules[deleteIndex - 1];
							        manager.deleteBySchedule(toDelete); // 검색된 일정과 동일한 일정을 전체 일정 목록에서 삭제
							        System.out.println("삭제가 완료되었습니다.");
							    } else {
							        System.out.println("잘못된 입력입니다.");
							    }
							    break;
							}
							else if(deleteAnswer.equals("아니요")) {
								break;
							}
							else {
								System.out.println("다시 입력해주세요.");
							}
						}
						break;
					} catch (Exception e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
					break;
				case 2: //해당 날짜들 사이에 있는 일정들 검색
					try {
						String searchDateInput = null, deleteAnswer = null;
						int searchYear = 0, searchMonth = 0, searchDay = 0;
						GregorianCalendar searchDateStart = new GregorianCalendar();
						GregorianCalendar searchDateEnd = new GregorianCalendar();
						
						boolean validInput = false;
						while (!validInput) {
							try {
								System.out.println("시작날짜를 입력해주세요. (ex) 2024월 02월 03일)(년,월,일 or 년,월 or 년)");
								searchDateInput = scan.nextLine().replaceAll(" ", "");	// 문자열에서 모든 띄어쓰기를 제거

								int yearIndex = searchDateInput.indexOf("년");// "년" 문자열의 인덱스 찾기
								searchYear = Integer.parseInt(searchDateInput.substring(0, yearIndex));// "년" 문자열 이전의 문자열을 정수로 변환

								if (yearIndex + 1 < searchDateInput.length()) {// "년" 문자열 뒤에 다른 문자가 있는지 확인
									int monthIndex = searchDateInput.indexOf("월", yearIndex);// "월" 문자열의 인덱스를 찾기
									searchMonth = Integer.parseInt(searchDateInput.substring(yearIndex + 1, monthIndex));// "년"과 "월" 사이의 부분 문자열을 정수로 변환

									if (monthIndex + 1 < searchDateInput.length()) {// "월" 문자열 뒤에 다른 문자가 있는지 확인
										int dayIndex = searchDateInput.indexOf("일", monthIndex);//"일" 문자열의 인덱스를 찾습니다.
										searchDay = Integer.parseInt(searchDateInput.substring(monthIndex + 1, dayIndex));// "월"과 "일" 사이의 부분 문자열을 정수로 변환

									}
								}

								if (searchMonth == 0 && searchDay == 0) {//년만 입력한 경우의 값넣기
									searchDateStart.set(Calendar.YEAR, searchYear);
									searchDateStart.set(Calendar.MONTH, searchMonth);
									searchDateStart.set(Calendar.DATE, searchDay);
								}
								else if (searchDay == 0) {//년, 월만 입력한 경우의 값넣기
									searchDateStart.set(Calendar.YEAR, searchYear);
									searchDateStart.set(Calendar.MONTH, searchMonth - 1);
									searchDateStart.set(Calendar.DATE, searchDay);
								}
								else {//모두 입력한 경우의 값넣기
									searchDateStart.set(Calendar.YEAR, searchYear);
									searchDateStart.set(Calendar.MONTH, searchMonth - 1 );
									searchDateStart.set(Calendar.DATE, searchDay - 1);
								}


								System.out.println("종료날짜를 입력해주세요. (ex) 2024년 02월 03일)(년,월,일 or 년,월 or 년)");
								searchDateInput = scan.nextLine().replaceAll(" ", "");	// 문자열에서 모든 띄어쓰기를 제거

								yearIndex = searchDateInput.indexOf("년");	// "년" 문자열의 인덱스 찾기
								searchYear = Integer.parseInt(searchDateInput.substring(0, yearIndex));// "년" 문자열 이전의 문자열을 정수로 변환

								if (yearIndex + 1 < searchDateInput.length()) {// "년" 문자열 뒤에 다른 문자가 있는지 확인
									int monthIndex = searchDateInput.indexOf("월", yearIndex); // "월" 문자열의 인덱스를 찾기
									searchMonth = Integer.parseInt(searchDateInput.substring(yearIndex + 1, monthIndex));// "년"과 "월" 사이의 부분 문자열을 정수로 변환

									if (monthIndex + 1 < searchDateInput.length()) {// "월" 문자열 뒤에 다른 문자가 있는지 확인
										int dayIndex = searchDateInput.indexOf("일", monthIndex);//"일" 문자열의 인덱스를 찾습니다.
										searchDay = Integer.parseInt(searchDateInput.substring(monthIndex + 1, dayIndex));// "월"과 "일" 사이의 부분 문자열을 정수로 변환

									}
								}
								if (searchMonth == 0 && searchDay == 0) {//년만 입력한 경우의 값넣기
									searchDateEnd.set(Calendar.YEAR, searchYear);
									searchDateEnd.set(Calendar.MONTH, searchMonth);
									searchDateEnd.set(Calendar.DATE, searchDay);
								}
								else if (searchDay == 0) {//년, 월만 입력한 경우의 값넣기
									searchDateEnd.set(Calendar.YEAR, searchYear);
									searchDateEnd.set(Calendar.MONTH, searchMonth - 1);
									searchDateEnd.set(Calendar.DATE, searchDay);
								}
								else {//모두 입력한 경우의 값넣기
									searchDateEnd.set(Calendar.YEAR, searchYear);
									searchDateEnd.set(Calendar.MONTH, searchMonth - 1);
									searchDateEnd.set(Calendar.DATE, searchDay - 1);
								}
								validInput = true;
							} catch (java.lang.StringIndexOutOfBoundsException e) {
								System.out.println("년,월,일 or 년,월 or 년 이라는 단어들을 사용해서 입력해주세요.");
							}
						}

						Schedule[] startAndEndSchedules = manager.search(searchDateStart, searchDateEnd);
						int[] indexes = manager.searchIndexes(searchDateStart, searchDateEnd);
						
						if (indexes.length > 0) { //검색결과 출력하기
							System.out.println("검색 결과:");
							for (int i = 0; i < indexes.length; i++) {
								System.out.println(startAndEndSchedules[indexes[i]].getYear() + "년 " + startAndEndSchedules[indexes[i]].getMonth() + "월 " + startAndEndSchedules[indexes[i]].getDay() + "일 : ");
								System.out.println("    일정 제목 : " + startAndEndSchedules[indexes[i]].getTitle() + " (" + startAndEndSchedules[indexes[i]].getImportance() + "/" + startAndEndSchedules[indexes[i]].getKinds() + ")");
								System.out.println("    시간 : " + startAndEndSchedules[indexes[i]].getStartTimeHour() + " : " + startAndEndSchedules[indexes[i]].getStartTimeMinute() + " ~ " + startAndEndSchedules[indexes[i]].getEndTimeHour() + " : " + startAndEndSchedules[indexes[i]].getEndTimeMinute());
								System.out.println("    메모 : " + startAndEndSchedules[indexes[i]].getMemo());
								System.out.println("--");
							}
						} else {
							System.out.println("검색 결과가 없습니다.");
							break;
						}
						
						while(true) {
							System.out.println("일정을 삭제하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //일정 삭제할 지 묻기
							deleteAnswer = scan.nextLine();


							if (deleteAnswer.equals("예")) {
								System.out.println("몇 번째 일정을 삭제하시겠습니까? (1부터 시작하며 숫자로 대답해주세요.)(ex) 1)"); // 삭제할 번호 물어보기
							    int deleteIndex = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리하기

							    if (deleteIndex >= 1 && deleteIndex <= startAndEndSchedules.length) {
							        Schedule toDelete = startAndEndSchedules[deleteIndex - 1];
							        manager.deleteBySchedule(toDelete); // 검색된 일정과 동일한 일정을 전체 일정 목록에서 삭제
							        System.out.println("삭제가 완료되었습니다.");
							    } else {
							        System.out.println("잘못된 입력입니다.");
							    }
							    break;
							}
							else if(deleteAnswer.equals("아니요")) {
								break;
							}
							else {
								System.out.println("다시 입력해주세요.");
							}
						}
						break;

					} catch (Exception e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
					break;
				case 3: // 해당 단어가 일정 제목 또는 일정 내용에 포함된 일정들 검색
					try {
						String searchWriting = null, deleteAnswer = null;
						
						System.out.println("검색하고 싶은 단어 및 내용을 입력해주세요.");
						searchWriting = scan.nextLine().replaceAll(" ", ""); // 문자열에서 모든 띄어쓰기를 제거

						Schedule[] writingSchedules = manager.search(searchWriting);
						int[] indexes = manager.searchIndexes(searchWriting);
						
						if (indexes.length > 0) { //검색결과 출력하기
							System.out.println("검색 결과:");
							for (int i = 0; i < indexes.length; i++) {
								System.out.println(writingSchedules[indexes[i]].getYear() + "년 " + writingSchedules[indexes[i]].getMonth() + "월 " + writingSchedules[indexes[i]].getDay() + "일 : ");
								System.out.println("    일정 제목 : " + writingSchedules[indexes[i]].getTitle() + " (" + writingSchedules[indexes[i]].getImportance() + "/" + writingSchedules[indexes[i]].getKinds() + ")");
								System.out.println("    시간 : " + writingSchedules[indexes[i]].getStartTimeHour() + " : " + writingSchedules[indexes[i]].getStartTimeMinute() + " ~ " + writingSchedules[indexes[i]].getEndTimeHour() + " : " + writingSchedules[indexes[i]].getEndTimeMinute());
								System.out.println("    메모 : " + writingSchedules[indexes[i]].getMemo());
								System.out.println("--");
							}
						} else {
							System.out.println("검색 결과가 없습니다.");
							break;
						}
						
						while(true) {
							System.out.println("일정을 삭제하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //일정 삭제할 지 묻기
							deleteAnswer = scan.nextLine();


							if (deleteAnswer.equals("예")) {
								System.out.println("몇 번째 일정을 삭제하시겠습니까? (1부터 시작하며 숫자로 대답해주세요.)(ex) 1)"); // 삭제할 번호 물어보기
							    int deleteIndex = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리하기

							    if (deleteIndex >= 1 && deleteIndex <= writingSchedules.length) {
							        Schedule toDelete = writingSchedules[deleteIndex - 1];
							        manager.deleteBySchedule(toDelete); // 검색된 일정과 동일한 일정을 전체 일정 목록에서 삭제
							        System.out.println("삭제가 완료되었습니다.");
							    } else {
							        System.out.println("잘못된 입력입니다.");
							    }
							    break;
							}
							else if(deleteAnswer.equals("아니요")) {
								break;
							}
							else {
								System.out.println("다시 입력해주세요.");
							}
						}
						break;

					} catch (Exception e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
					break;
				case 4: // 해당 우선순위인 일정들 검색
					try {
						String searchPriorityInput = null, deleteAnswer = null;
						int searchPriority = 0;

						boolean validInput = false;
						while (!validInput) {
							try {
								System.out.println("검색하고 싶은 우선순위를 입력해주세요. (상 or 중 or 하 를 입력해주세요.)(ex) 상)");
								searchPriorityInput = scan.nextLine();	//우선순위 입력 받기

								switch (searchPriorityInput) {
								case "상":	//상은 1로
									searchPriority = 1;
									break;
								case "중":	//중은 2로
									searchPriority = 2;
									break;
								case "하":	//하는 3로
									searchPriority = 3;
									break;
								default:
									throw new IllegalArgumentException("유효하지 않은 우선순위입니다.");
								}
								validInput = true;
							} catch (IllegalArgumentException e) {
								System.out.println(e.getMessage());
								System.out.println("상 or 중 or 하 중에서 하나를 입력해주세요.");
							}
						}

						Schedule[] prioritySchedules = manager.search(searchPriority);
						int[] indexes = manager.searchIndexes(searchPriority);
						
						if (indexes.length > 0) { //검색결과 출력하기
							System.out.println("검색 결과:");
							for (int i = 0; i < indexes.length; i++) {
								System.out.println(prioritySchedules[indexes[i]].getYear() + "년 " + prioritySchedules[indexes[i]].getMonth() + "월 " + prioritySchedules[indexes[i]].getDay() + "일 : ");
								System.out.println("    일정 제목 : " + prioritySchedules[indexes[i]].getTitle() + " (" + prioritySchedules[indexes[i]].getImportance() + "/" + prioritySchedules[indexes[i]].getKinds() + ")");
								System.out.println("    시간 : " + prioritySchedules[indexes[i]].getStartTimeHour() + " : " + prioritySchedules[indexes[i]].getStartTimeMinute() + " ~ " + prioritySchedules[indexes[i]].getEndTimeHour() + " : " + prioritySchedules[indexes[i]].getEndTimeMinute());
								System.out.println("    메모 : " + prioritySchedules[indexes[i]].getMemo());
								System.out.println("--");
							}
						} else {
							System.out.println("검색 결과가 없습니다.");	//검색 결과 츌력하기
							break;
						}
						
						while(true) {
							System.out.println("일정을 삭제하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //일정 삭제할 지 묻기
							deleteAnswer = scan.nextLine();


							if (deleteAnswer.equals("예")) {
								System.out.println("몇 번째 일정을 삭제하시겠습니까? (1부터 시작하며 숫자로 대답해주세요.)(ex) 1)"); // 삭제할 번호 물어보기
							    int deleteIndex = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리하기

							    if (deleteIndex >= 1 && deleteIndex <= prioritySchedules.length) {
							        Schedule toDelete = prioritySchedules[deleteIndex - 1];
							        manager.deleteBySchedule(toDelete); // 검색된 일정과 동일한 일정을 전체 일정 목록에서 삭제
							        System.out.println("삭제가 완료되었습니다.");
							    } else {
							        System.out.println("잘못된 입력입니다.");
							    }
							    break;
							}
							else if(deleteAnswer.equals("아니요")) {
								break;
							}
							else {
								System.out.println("다시 입력해주세요.");
							}
						}
						break;

					} catch (Exception e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
					break;
				case 5: // 해당 카테고리의 일정들 검색 (학업, 운동, 회의, 취미, 기타 에서 선택해주세요.)
					try {
						String searchKindInput = null, deleteAnswer = null;
						char searchKind = 0;

						boolean validInput = false;
						while (!validInput) {
							try {
								System.out.println("검색하고 싶은 일정의 종류를 입력해주세요. (ex) 학업)(학업 or 운동 or 회의 or 취미 or 기타)");
								searchKindInput = scan.nextLine();	//종류 입력 받기

								switch (searchKindInput) {
								case "학업": //학업은 A로
									searchKind = 'A';
									break;
								case "운동": //운동은 E로
									searchKind = 'E';
									break;
								case "회의": //회의는 M로
									searchKind = 'M';
									break;
								case "취미": //취미는 H로
									searchKind = 'H';
									break;
								case "기타": //기타는 O로
									searchKind = 'O';
									break;
								default:
									throw new IllegalArgumentException("유효하지 않은 종류입니다.");
								}
								validInput = true;
							} catch (IllegalArgumentException e) {
								System.out.println("잘못된 입력입니다. 다시 입력하세요.");
							}
						}
						Schedule[] kindSchedules = manager.search(searchKind);
						int[] indexes = manager.searchIndexes(searchKind);
						
						if (indexes.length > 0) { //검색결과 출력하기
							System.out.println("검색 결과:");
							for (int i = 0; i < indexes.length; i++) {
								System.out.println(kindSchedules[indexes[i]].getYear() + "년 " + kindSchedules[indexes[i]].getMonth() + "월 " + kindSchedules[indexes[i]].getDay() + "일 : ");
								System.out.println("    일정 제목 : " + kindSchedules[indexes[i]].getTitle() + " (" + kindSchedules[indexes[i]].getImportance() + "/" + kindSchedules[indexes[i]].getKinds() + ")");
								System.out.println("    시간 : " + kindSchedules[indexes[i]].getStartTimeHour() + " : " + kindSchedules[indexes[i]].getStartTimeMinute() + " ~ " + kindSchedules[indexes[i]].getEndTimeHour() + " : " + kindSchedules[indexes[i]].getEndTimeMinute());
								System.out.println("    메모 : " + kindSchedules[indexes[i]].getMemo());
								System.out.println("--");
							}
						} 
						else {
							System.out.println("검색 결과가 없습니다.");
							break;
						}
						
						while(true) {
							System.out.println("일정을 삭제하시겠습니까? (예 또는 아니요 로 대답해주세요.)(ex) 예)"); //일정 삭제할 지 묻기
							deleteAnswer = scan.nextLine();


							if (deleteAnswer.equals("예")) {
								System.out.println("몇 번째 일정을 삭제하시겠습니까? (1부터 시작하며 숫자로 대답해주세요.)(ex) 1)"); // 삭제할 번호 물어보기
							    int deleteIndex = scan.nextInt();
							    scan.nextLine(); // 개행 문자 처리하기

							    if (deleteIndex >= 1 && deleteIndex <= kindSchedules.length) {
							        Schedule toDelete = kindSchedules[deleteIndex - 1];
							        manager.deleteBySchedule(toDelete); // 검색된 일정과 동일한 일정을 전체 일정 목록에서 삭제
							        System.out.println("삭제가 완료되었습니다.");
							    } else {
							        System.out.println("잘못된 입력입니다.");
							    }
							    break;
							}
							else if(deleteAnswer.equals("아니요")) {
								break;
							}
							else {
								System.out.println("다시 입력해주세요.");
							}
						}
						break;
						
					} catch (Exception e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
					break;
				}
				break;
			case 3:	//일정 전체 출력
				try {
					System.out.println("'3. 일정 전체 출력'을 선택하셨습니다.");	//선택한 메뉴 안내하기 

					//전체 츌력하기
					if (manager.getSchedule().length == 0) {
						throw new NullPointerException("일정이 없습니다.");
					} else {
						for (int i = 0; i < manager.getSchedule().length; i++) {
							System.out.println(manager.getSchedule(i).getYear() + "년 " + manager.getSchedule(i).getMonth() + "월 " + manager.getSchedule(i).getDay() + "일 : "); // Print year, month, and day
							System.out.println("    " + "일정 제목 : " + manager.getSchedule(i).getTitle() + " (" + manager.getSchedule(i).getImportance() + "/" + manager.getSchedule(i).getKinds() + ")");
							System.out.println("    " + "시간 : " + manager.getSchedule(i).getStartTimeHour() + " : " + manager.getSchedule(i).getStartTimeMinute() + " ~ " + manager.getSchedule(i).getEndTimeHour() + " : " + manager.getSchedule(i).getEndTimeMinute());
							System.out.println("    " + "메모 : " + manager.getSchedule(i).getMemo());
							System.out.println("--");
						}
					}
				} catch (java.lang.NullPointerException e) {
					System.out.println("1월부터 12월까지 일정이 없습니다. 일정을 추가하고 출력해주세요.");
				}
				break;
			case 4: // 종료
				System.out.println("'4. 종료'를 선택하셨습니다.");	//선택한 메뉴 안내하기 
				System.out.println("프로그램을 종료합니다.");
				scan.close();
				System.exit(0);	//프로그램 종료하기 
			default:	//그 외
				System.out.println("잘못된 입력입니다. 메뉴 번호를 확인하세요.");
				break;
			}
		}
	}
}
