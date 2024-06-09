class Schedule {
    private String year, month, day, startTimeHour, startTimeMinute, endTimeHour, endTimeMinute, title, importance, kinds, memo;
    
    //생성자 함수 
    Schedule(String year, String month, String day, String title, String importance, String startTimeHour, String startTimeMinute, String endTimeHour, String endTimeMinute, String kinds, String memo) {
        this.year = year;
    	this.month = month;
        this.day = day;
        this.title = title;
        this.importance = importance;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.kinds = kinds;
        this.memo = memo;
    }
    
    //get 함수들
    
    String getYear() {
        return year;
    }
    
    String getMonth() {
        return month;
    }

    String getDay() {
        return day;
    }

    String getTitle() {
        return title;
    }

    String getImportance() {
        return importance;
    }

    String getStartTimeHour() {
        return startTimeHour;
    }

    String getStartTimeMinute() {
        return startTimeMinute;
    }

    String getEndTimeHour() {
        return endTimeHour;
    }

    String getEndTimeMinute() {
        return endTimeMinute;
    }

    String getKinds() {
        return kinds;
    }

    String getMemo() {
        return memo;
    }

    // set 함수들
    void setYear(String year) {
        this.year = year;
    }
    
    void setMonth(String month) {
        this.month = month;
    }

    void setDay(String day) {
        this.day = day;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setImportance(String importance) {
        this.importance = importance;
    }

    void setStartTimeHour(String startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    void setStartTimeMinute(String startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    void setEndTimeHour(String endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    void setEndTimeMinute(String endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }

    void setKinds(String kinds) {
        this.kinds = kinds;
    }

    void setMemo(String memo) {
        this.memo = memo;
    }
    
    public boolean equals(Object obj) { //외부에서도 객체 간의 동등성을 확인하기 위해 public 사용
        if (this == obj) {
        	return true; //객체가 자기 자신과 같은지 확인(같다면, 추가적인 비교를 하지 않고 true를 반환)
        }
        if (obj == null || getClass() != obj.getClass()) {
        	return false; //비교 대상이 null이거나 클래스가 다른 경우, false를 반환
        }
        Schedule schedule = (Schedule) obj; //obj를 Schedule 타입으로 캐스팅
        return title.equals(schedule.title); //현재 객체의 일정 제목과 비교 대상 객체의 일정 제목을 비교(두 일정의 제목이 동일하면 true를 반환)
    }
   
}
