import java.util.Calendar; //Calendar 클래스를 사용하기 위한 임포트문
import java.util.GregorianCalendar; //GregorianCalendar 클래스를 사용하기 위한 임포트문

public class ScheduleManagement {
    
    private Schedule[] schedule; // 전체 일정을 넣을 배열
    private int currentSize; // 현재 저장된 일정의 개수

    // 생성자: 스케줄 리스트의 크기를 설정하는 생성자
    ScheduleManagement(int size) {
        schedule = new Schedule[size];
        currentSize = 0;
    }
    
    //중복되는 일정이 있는지 검사하기
    boolean isDuplicate(Schedule newSchedule) {
        for (Schedule schedule : this.schedule) {
            if (schedule != null && schedule.equals(newSchedule)) {// 현재 스케줄이 null이 아니고, 같은 스케줄이 있다면, 중복으로 판단하고 true를 반환
                return true;
            }
        }
        return false; // 모든 스케줄을 확인한 후에도 중복된 스케줄이 없으면 false를 반환
    }

    // 일정 추가하기
    void addSchedule(Schedule s) {
    	schedule[currentSize] = s;
        currentSize++;
        sortScheduleByDateAndTime(); // 추가된 일정을 정렬
    }

    // 주어진 일정과 동일한 일정을 전체 일정 배열에서 찾아 삭제
    void deleteBySchedule(Schedule schedule) {
        for (int i = 0; i < this.schedule.length; i++) {// 전체 일정 배열을 반복
            if (this.schedule[i] != null && this.schedule[i].equals(schedule)) {// 현재 요소가 null이 아니고 주어진 일정과 동일한지 확인
                delete(i); // 일치하는 일정을 삭제
            }
        }
    }
    
    // 일정 제거하기
    void delete(int index) {
        if (index >= 0 && index < currentSize && schedule[index] != null) { // 현재 저장된 일정 개수를 사용하여 범위 확인
            for (int i = index; i < currentSize - 1; i++) { // currentSize를 사용하여 반복 횟수 결정
                schedule[i] = schedule[i + 1];
            }
            schedule[currentSize - 1] = null; // 마지막 일정 삭제 후 null로 초기화
            currentSize--; // 현재 저장된 일정 개수 감소
        }
    }

    // 일정 정렬하기
    void sortScheduleByDateAndTime() {
        int n = currentSize;	// 현재 스케줄의 크기를 n에 저장합니다.
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {	// 배열을 반복하면서 인접한 두 요소를 비교
                if (compareYears(schedule[i - 1], schedule[i]) > 0) { // 연도 비교
                    swapSchedules(i - 1, i);
                    swapped = true;
                } else if (compareYears(schedule[i - 1], schedule[i]) == 0) {// 만약 두 연도가 같다면 월을 비교
                    if (compareMonths(schedule[i - 1], schedule[i]) > 0) {
                        swapSchedules(i - 1, i);
                        swapped = true;
                    } else if (compareMonths(schedule[i - 1], schedule[i]) == 0) {// 만약 두 월이 같다면 일을 비교
                        if (compareDays(schedule[i - 1], schedule[i]) > 0) {
                            swapSchedules(i - 1, i);
                            swapped = true;
                        } else if (compareDays(schedule[i - 1], schedule[i]) == 0) {// 만약 두 일이 같다면 시간을 비교
                            if (compareTimes(schedule[i - 1], schedule[i]) > 0) {
                                swapSchedules(i - 1, i);
                                swapped = true;
                            }
                        }
                    }
                }
            }
            n--;
        } while (swapped);
    }

    // 두 일정을 교환하기
    void swapSchedules(int index1, int index2) {
        Schedule temp = schedule[index1];
        schedule[index1] = schedule[index2];
        schedule[index2] = temp;
    }

    // 연도를 비교하기
    int compareYears(Schedule s1, Schedule s2) {
        return Integer.parseInt(s1.getYear()) - Integer.parseInt(s2.getYear());
    }

    // 월을 비교하기
    int compareMonths(Schedule s1, Schedule s2) {
        return Integer.parseInt(s1.getMonth()) - Integer.parseInt(s2.getMonth());
    }

    // 일을 비교하기
    int compareDays(Schedule s1, Schedule s2) {
        return Integer.parseInt(s1.getDay()) - Integer.parseInt(s2.getDay());
    }

    // 시작 시간을 비교하기
    int compareTimes(Schedule s1, Schedule s2) {
        int hour1 = Integer.parseInt(s1.getStartTimeHour());
        int minute1 = Integer.parseInt(s1.getStartTimeMinute());
        int hour2 = Integer.parseInt(s2.getStartTimeHour());
        int minute2 = Integer.parseInt(s2.getStartTimeMinute());

        if (hour1 == hour2) {
            return minute1 - minute2;
        } else {
            return hour1 - hour2;
        }
    }

    // 특정 인덱스의 일정을 반환하기
    Schedule getSchedule(int index) {
        if (index >= 0 && index < currentSize) {
            return schedule[index];
        } else {
            return null; // 인덱스가 유효하지 않으면 null 반환하기
        }
    }

    // 전체 일정 배열을 반환하기
    Schedule[] getSchedule() {
        Schedule[] result = new Schedule[currentSize];
        System.arraycopy(schedule, 0, result, 0, currentSize);
        return result;
    }
    
    // 특정 날짜 이후의 일정 검색하기
    Schedule[] search(GregorianCalendar g) {
        Schedule[] result = new Schedule[currentSize];	// 결과를 저장할 배열 생성
        int count = 0;

        for (int i = 0; i < currentSize; i++) {
            int scheduleYear = Integer.parseInt(schedule[i].getYear());
            if (scheduleYear > g.get(Calendar.YEAR)) {	// 일정의 연도가 주어진 연도보다 크다면
                result[count++] = schedule[i];
            } else if (scheduleYear == g.get(Calendar.YEAR)) {
                // 같은 연도인 경우에만 월과 일을 비교
                int scheduleMonth = Integer.parseInt(schedule[i].getMonth());
                int scheduleDay = Integer.parseInt(schedule[i].getDay());

                if (g.isSet(Calendar.MONTH) && (g.get(Calendar.MONTH) + 1) < scheduleMonth) {// 일정의 월이 주어진 월보다 크다면
                    result[count++] = schedule[i];
                } else if (g.isSet(Calendar.DAY_OF_MONTH) && g.get(Calendar.MONTH) + 1 == scheduleMonth && g.get(Calendar.DAY_OF_MONTH) < scheduleDay) {
                    result[count++] = schedule[i];
                }
            }
        }

        Schedule[] finalResult = new Schedule[count]; // 결과 배열의 크기를 실제 결과 수에 맞게 조정
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }

    
    // 특정 기간 내의 일정 검색하기
    Schedule[] search(GregorianCalendar start, GregorianCalendar end) {
        Schedule[] result = new Schedule[currentSize]; // 결과를 저장할 배열 생성
        int count = 0;

        for (int i = 0; i < currentSize; i++) {
            GregorianCalendar scheduleDate = new GregorianCalendar(Integer.parseInt(schedule[i].getYear()), Integer.parseInt(schedule[i].getMonth()) - 1, Integer.parseInt(schedule[i].getDay())); // 일정의 날짜를 GregorianCalendar 객체로 변환
            if (!scheduleDate.before(start) && !scheduleDate.after(end)) {	// 일정이 시작 날짜와 종료 날짜 사이에 있다면
                result[count++] = schedule[i]; //데이터 넣기
            }
        }

        Schedule[] finalResult = new Schedule[count];	// 결과 배열의 크기를 실제 결과 수에 맞게 조정
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }

    // 특정 문자열을 포함하는 일정 검색하기
    Schedule[] search(String s) {
        Schedule[] result = new Schedule[currentSize];// 결과를 저장할 배열 생성
        int count = 0;

        for (int i = 0; i < currentSize; i++) {
            if (schedule[i].getTitle().replaceAll(" ", "").contains(s) || schedule[i].getMemo().replaceAll(" ", "").contains(s)) {	// 일정의 제목 또는 메모에 주어진 문자열이 포함되어 있다면
                result[count++] = schedule[i];	//데이터 넣기
            }
        }

        Schedule[] finalResult = new Schedule[count];// 결과 배열의 크기를 실제 결과 수에 맞게 조정
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }

    // 특정 우선순위의 일정 검색하기
    Schedule[] search(int priority) {
        Schedule[] result = new Schedule[currentSize];// 결과를 저장할 배열 생성
        int count = 0;
        String priorityString;

        switch (priority) {	 // 우선순위를 문자열로 변환합니다
            case 1:
                priorityString = "상";
                break;
            case 2:
                priorityString = "중";
                break;
            case 3:
                priorityString = "하";
                break;
            default:
                return new Schedule[0]; // 잘못된 우선순위인 경우 빈 배열 반환
        }

        for (int i = 0; i < currentSize; i++) {
            if (schedule[i].getImportance().equals(priorityString)) {	// 일정의 중요도가 주어진 우선순위와 같다면
                result[count++] = schedule[i];
            }
        }

        Schedule[] finalResult = new Schedule[count];	// 결과 배열의 크기를 실제 결과 수에 맞게 조정
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }

    // 특정 카테고리의 일정 검색하기
    Schedule[] search(char category) {
        Schedule[] result = new Schedule[currentSize];// 결과를 저장할 배열 생성
        int count = 0;

        String categoryString;
        switch (category) { 
            case 'A': 
                categoryString = "학업";
                break;
            case 'E':
                categoryString = "운동";
                break;
            case 'M':
                categoryString = "회의";
                break;
            case 'H':
                categoryString = "취미";
                break;
            case 'O':
                categoryString = "기타";
                break;
            default:
                return new Schedule[0]; // 잘못된 카테고리인 경우 빈 배열 반환
        }

        for (int i = 0; i < currentSize; i++) {
            if (schedule[i].getKinds().equals(categoryString)) {// 일정의 종류가 주어진 종류와 같다면
                result[count++] = schedule[i]; //데이터 넣기
            }
        }

        Schedule[] finalResult = new Schedule[count];// 결과 배열의 크기를 실제 결과 수에 맞게 조정
        System.arraycopy(result, 0, finalResult, 0, count);
        return finalResult;
    }
    
    //해당 날짜 이후 일정들의 index들을 return (복수 삭제일 때 사용할 예정)
    int[] searchIndexes(GregorianCalendar g) {
        Schedule[] schedules = search(g); // 해당 날짜 이후의 일정들 검색
        int[] indexes = new int[schedules.length]; // 인덱스들을 저장할 배열 생성
        for (int i = 0; i < schedules.length; i++) {
            indexes[i] = findIndex(schedules, schedules[i]); // 검색된 일정들의 인덱스를 찾아서 배열에 저장
        }
        return indexes;
    }

    //해당 날짜 사이에 있는 일정들의 index들을 return (복수 삭제일 때 사용할 예정)
    int[] searchIndexes(GregorianCalendar start, GregorianCalendar end) {
        Schedule[] schedules = search(start, end); // 해당 기간 내의 일정들 검색
        int[] indexes = new int[schedules.length]; // 인덱스들을 저장할 배열 생성
        for (int i = 0; i < schedules.length; i++) {
            indexes[i] = findIndex(schedules, schedules[i]); // 검색된 일정들의 인덱스를 찾아서 배열에 저장
        }
        return indexes;
    }

    // 해당 스트링이 일정제목 또는 일정내용에 포함되어 있는 일정들의 index들을 return (복수 삭제일 때 사용할 예정)
    int[] searchIndexes(String s) {
        Schedule[] schedules = search(s); // 해당 문자열을 포함하는 일정들 검색
        int[] indexes = new int[schedules.length]; // 인덱스들을 저장할 배열 생성
        for (int i = 0; i < schedules.length; i++) {
            indexes[i] = findIndex(schedules, schedules[i]); // 검색된 일정들의 인덱스를 찾아서 배열에 저장
        }
        return indexes;
    }

    // 해당 우선순위의 일정들의 index들을 return (복수 삭제일 때 사용할 예정)
    int[] searchIndexes(int priority) {
        Schedule[] schedules = search(priority); // 해당 우선순위의 일정들 검색
        int[] indexes = new int[schedules.length]; // 인덱스들을 저장할 배열 생성
        for (int i = 0; i < schedules.length; i++) {
            indexes[i] = findIndex(schedules, schedules[i]); // 검색된 일정들의 인덱스를 찾아서 배열에 저장
        }
        return indexes;
    }

    // 해당 카테고리의 일정들의 index들을 return (복수 삭제일 때 사용할 예정)
    int[] searchIndexes(char category) {
        Schedule[] schedules = search(category); // 해당 카테고리의 일정들 검색
        int[] indexes = new int[schedules.length]; // 인덱스들을 저장할 배열 생성
        for (int i = 0; i < schedules.length; i++) {
            indexes[i] = findIndex(schedules, schedules[i]); // 검색된 일정들의 인덱스를 찾아서 배열에 저장
        }
        return indexes;
    }
    
    // 해당 날짜 이후 일정들의 첫번째 index(위치)를 return//이 코드를 최종적으로 안 쓰게 되면 지울 예정
    int searchIndex(GregorianCalendar g) {
        Schedule[] schedules = search(g); // 해당 날짜 이후의 일정들 검색
        if (schedules.length > 0) {
            // 검색된 일정이 있을 경우 첫 번째 일정의 인덱스를 반환
            return findIndex(schedule, schedules[0]);
        } else {
            // 검색된 일정이 없을 경우 -1 반환
            return -1;
        }
    }

    // 해당 날짜 사이에 있는 일정들의 첫번째 index(위치)를 return//이 코드를 최종적으로 안 쓰게 되면 지울 예정
    int searchIndex(GregorianCalendar start, GregorianCalendar end) {
        Schedule[] schedules = search(start, end); // 해당 기간 내의 일정들 검색
        if (schedules.length > 0) {
            // 검색된 일정이 있을 경우 첫 번째 일정의 인덱스를 반환
            return findIndex(schedule, schedules[0]);
        } else {
            // 검색된 일정이 없을 경우 -1 반환
            return -1;
        }
    }

    // 해당 스트링이 일정제목 또는 일정내용에 포함되어 있는 일정들의 첫번때 index(위치)를 return//이 코드를 최종적으로 안 쓰게 되면 지울 예정
    int searchIndex(String s) {
        Schedule[] schedules = search(s); // 해당 문자열을 포함하는 일정들 검색
        if (schedules.length > 0) {
            // 검색된 일정이 있을 경우 첫 번째 일정의 인덱스를 반환
            return findIndex(schedule, schedules[0]);
        } else {
            // 검색된 일정이 없을 경우 -1 반환
            return -1;
        }
    }

    // 해당 우선순위의 일정들의 첫번째 index(위치)를 return//이 코드를 최종적으로 안 쓰게 되면 지울 예정
    int searchIndex(int priority) {
        Schedule[] schedules = search(priority); // 해당 우선순위의 일정들 검색
        if (schedules.length > 0) {
            // 검색된 일정이 있을 경우 첫 번째 일정의 인덱스를 반환
            return findIndex(schedule, schedules[0]);
        } else {
            // 검색된 일정이 없을 경우 -1 반환
            return -1;
        }
    }

    // 해당 카테고리의 일정들의 첫번째  index(위치)를 return  //이 코드를 최종적으로 안 쓰게 되면 지울 예정
    int searchIndex(char category) {
        Schedule[] schedules = search(category); // 해당 카테고리의 일정들 검색
        if (schedules.length > 0) {
            // 검색된 일정이 있을 경우 첫 번째 일정의 인덱스를 반환
            return findIndex(schedule, schedules[0]);
        } else {
            // 검색된 일정이 없을 경우 -1 반환
            return -1;
        }
    }
    
    // 해당 일정의 인덱스를 찾아서 반환
    int findIndex(Schedule[] allSchedules, Schedule schedule) {
        for (int i = 0; i < allSchedules.length; i++) {
            if (allSchedules[i].equals(schedule)) { // 해당 일정과 동일한 일정을 찾으면 인덱스 반환
                return i;
            }
        }
        return -1; // 일정을 찾지 못한 경우
    }
}
