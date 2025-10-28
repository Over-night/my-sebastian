package moe.overnight.my_sebastian.common.enums;

public interface ResponseMessage {
    String getMessage();

    ResponseMessage MY_TASKS_SUMMARY_INQUIRE = () -> "내 작업 요약 조회가 완료되었습니다.";
    ResponseMessage TEAM_PROGRESS_INQUIRE = () -> "팀 진행상황 조회가 완료되었습니다.";
    ResponseMessage INTEGRATED_SEARCH_COMPLETED = () -> "통합 검색이 완료되었습니다.";
    ResponseMessage DASHBOARD_STATS_INQUIRE = () -> "대시보드 통계 조회가 완료되었습니다.";
}