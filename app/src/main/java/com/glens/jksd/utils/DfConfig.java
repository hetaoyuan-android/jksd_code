package com.glens.jksd.utils;

/**
 * @author sunyan
 */

public class DfConfig {

    public static final String FIRST_IN_APP = "frist_in_app";// 第一次进入应用

//    /**
//     * 开发环境
//     */
//    public static final DevMode mode = DevMode.DEV;

    /**
     * 图片url
     */
    public static String IMAGE_URL;
    /**
     * 请求根地址
     */
    public static String BASE_URL;
    /**
     * 报告url
     */
    public static String REPORT_URL;
    public static String NOTICEPIC_URL;

//    static {
//        switch (mode) {
//            case DEV:
//                BASE_URL = "http://172.16.2.59:8080/pmsWeb";
////                BASE_URL = "http://cloudos.glens.com.cn/omsWeb";
//                /**
//                 * 谢立
//                 * 123456
//                 */
//                break;
//            case TEST:
//                BASE_URL = "http://hdas.hsgene.cn";
//                break;
//            case RELEASE:
//                BASE_URL = "https://hdas.hsgene.com";
//                break;
//            default:
//                break;
//        }
//    }

    public static final String IMAGE_SLIM = "?imageslim";

    public static final String IMAGE_THUMBNAIL = "?imageMogr2/size-limit/15k!";

    public static final String X_HDAS_OS_TYPE = "android";
//    /**
//     * 开启debug模式
//     */
//    public static final boolean IS_DEBUG = (mode != DevMode.RELEASE);

    /**
     * shared文件名称
     */
    public static final String SHARED_SETTINGS = "shared_settings";
    /**
     * 用户信息
     */
    public static final String USER_INFO = "userInfo";

    /**
     * 用户token
     */
    public static final String USER_TOKEN = "userToken";

    /**
     * 用户名
     */
    public static final String USER_NAME = "userName";

    public static final String ACCOUNT_CODE = "accountCode";

    public static final String COMPANY_CODE = "companyCode";

    public static final String EMPLOYEE_CODE = "employeeCode";

    public static final String EMPLOYEE_NAME = "employeeName";

    public static final String SPEECH_CRAFT = "speech_craft";

    public static final String SPEECH_CRAFT1 = "speech_craft1";

    public static final String SPEECH_CRAFT2 = "speech_craft2";

    public static final String CURRENTTYPE = "currentType";

    public static final String ROLE_CODE = "roleCode";

    public static final String isProduction = "isProduction";

    public static final String unitCode = "unitCode";

    public static final String roleCode = "roleCode";

    //组织结构
    public static final String UNIT_NAME = "unitName";
    public static final String UNIT_CODE = "unitCode";
    //专业组
    public static final String PRO_GROUP = "proGroup";
    //职务
    public static final String JOB_CODE = "jobCode";

    /**
     * 省市区
     */
    public static final String REGION = "region";

    public static final String DATE_REEGION = "date_region";

    /**
     * 身份证正则表达式
     */
    public static final String CERTIFY_REGEX = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
    /**
     * PickerView ResultCode清空数据
     */
    public static final int PICKER_CLEAR_DATA = 1852;
    /**
     * shape存储字典key
     */
    public static final String DICT_ROOT = "Dict_root_";

    public static final String DICT_DATE = "Dict_date_";

    public static final String CANCER_LIST = "cancer_list";

    public static final String MEDICINE_LIST = "medicine_list";

    public static final String MEDICINE_NATURE = "medicine_nature";

    public static final String DRUG_PRODUCT_TYPE = "drug_product_type";

    public static final String DRUG_CANCER_TYPE = "drug_cancer_type";

    public static final int TREATMENT_ADDRESS = 1013;


    /****
     * 网络配置项
     */

    /**
     * 成功
     */
    public static final int NET_CODE_OK = 200;
    /**
     * 无数据
     */
    public static final int NET_CODE_404 = 404;
    /**
     * 用户不存在
     */
    public static final int NET_CODE_10002 = 10002;
    /**
     * 当前用户已经被禁用
     */
    public static final int NET_CODE_10004 = 10004;
    /**
     * 当前用户已经离职
     */
    public static final int NET_CODE_10005 = 10005;
    /**
     * token失效
     */
    public static final int NET_CODE_50001 = 50001;
    public static final int NET_CODE_50002 = 50002;
    public static final int NET_CODE_50003 = 50003;

    /**
     * 登录
     */
    public static final String NET_LOGIN = "eap/pmsServices/loginService";
    /**
     * 获取首页菜单栏
     */
    public static final String NET_GET_HOMELISGT_APP = "eap/pmsServices/pwBaoAppService/lightApp";
    /**
     * 获取任务单列表
     */
    public static final String NET_TASK_SHEET = "eap/pmsServices/oms/getOmTaskOrderPages";
    /**
     * 获取片区列表
     */
    public static final String NET_GET_DISTRICT = "eap/pmsServices/oms/getOmDistrict";
    /**
     * 获取片区列表(工程)
     */
    public static final String NET_GET_PERSON_DISTRICT = "eap/pmsServices/oms/getPersonDistrict";
    /**
     * 获取工作日志列表
     */
    public static final String NET_GET_WORK_LOG = "eap/pmsServices/oms/getWorkDFailyRecfordsPages";
    /**
     * 获取请假管理列表
     */
    public static final String NET_GET_LEAVE_MANAGE = "eap/pmsServices/oms/getOmLeavePages";
    /**
     * 获取文件库列表
     */
    public static final String NET_GET_FILE_LIBRARY = "eap/pmsServices/knowledge/selectCatalogAllWhitPhone";
    /**
     * 新增工作日志
     */
    public static final String NET_POST_NEWLY_ADDED_WORK_LOG = "eap/pmsServices/oms/addDailyRecord";
    /**
     * 新增任务单
     */
    public static final String NET_ADD_TASK_SHEET = "eap/pmsServices/oms/addOmTaskOrder";
    /**
     * 获取工程分页列表
     */
    public static final String NET_ENGINEER_LIST = "eap/pmsServices/oms/getOmProjectPages";
    /**
     * 新增工程
     */
    public static final String NET_ADD_ENGINEER = "eap/pmsServices/oms/addOmProject";
    /**
     * 获取工程准备阶段文档
     */
    public static final String NET_ENGINEER_PREPARE_DOCS_LIST = "eap/pmsServices/oms/getOmProjectPrepareDocs";
    /**
     * 获取工程施工阶段材料
     */
    public static final String NET_GET_OMCONSTRUCTION_PHASE_MATERIAL_LIST = "eap/pmsServices/oms/getOmConstructionPhaseMaterial";
    /**
     * 获取工程施工竣工阶段材料
     */
    public static final String NET_GET_OMCOMPLETION_PHASE_MATERIAL_LIST = "eap/pmsServices/oms/getOmCompletionPhaseMaterial";
    /**
     * 获取工程详情
     */
    public static final String NET_ENGINEER_DETAIL = "eap/pmsServices/oms/getOmProjectDetail";
    /**
     * 提交工程信息
     */
    public static final String NET_ENGINEER_INFO_SUBMIT = "eap/pmsServices/oms/updateOmProject";
    /**
     * 提交位置信息
     */
    public static final String NET_LCAOTION_INFO_SUBMIT = "eap/pmsServices/oms/insertOmProjectXY";
    /**
     * 新增请假信息
     */
    public static final String NET_POST_NEWLY_ADDED_LEAVE_INFO = "eap/pmsServices/oms/addOmLeave";

    /**
     * 新增工程施工阶段材料
     */
    public static final String NET_OMCONSTRUCTION_PHASEMATERIAL = "eap/pmsServices/oms/addOmConstructionPhaseMaterial";
    /**
     * 获取任务单详情
     */
    public static final String NET_TASKSHEET_DETAIL = "eap/pmsServices/oms/getOmTaskOrderDetails";
    /**
     * 获取当前人签到记录
     */
    public static final String NET_GET_ATTENDANCE_RECORD = "eap/pmsServices/oms/isCheckInOrCheckOut";
    /**
     * 新增考勤记录
     */
    public static final String NET_POST_NEWLY_ADDED_ATTENDANCE_RECORD = "eap/pmsServices/oms/addCheckingAttendance";
    /**
     * 获取文件列表
     */
    public static final String NET_GET_FILE_LIST = "eap/pmsServices/knowledgeManager/selectManagerAll";
    /**
     * 获取部门
     */
    public static final String NET_GET_DEPARTMENT = "eap/ptiServices/opsMg/teamGroupMg/teamGroupLinkLine/getUnits";
    /**
     * 新增工程竣工阶段材料
     */
    public static final String NET_OMCOMPLETION_PHASEMATERIAL = "eap/pmsServices/oms/addCompletionPhaseMaterial";
    /**
     * 根据材料类型  获取列表(施工阶段)
     */
    public static final String NET_MATERIAL_CONSTRUCTION_LIST = "eap/pmsServices/oms/getOmConstructionPhaseMaterialByCode";
    /**
     * 根据材料类型  获取列表(竣工阶段)
     */
    public static final String NET_MATERIAL_COMPLETION_LIST = "eap/pmsServices/oms/getOmCompletionPhaseMaterialByCode";
    /**
     * 獲取文件
     */
    public static final String NET_GET_FIL = "eap/pmsServices/knowledgeManager/selectManagerById";
    /**
     * 获取会议列表
     */
    public static final String NET_GET_PROCEEDINGS_LIST = "eap/pmsServices/oms/getMeetingRecord";
    /**
     * 获取会议记录详情
     */
    public static final String NET_GET_PROCEEDINGS_DETAIL = "eap/pmsServices/oms/getMeetingRecordDetails";
    /**
     * 新增会议记录
     */
    public static final String NET_ADD_PROCEEDINGS = "eap/pmsServices/oms/insertMeetingRecord";
    /**
     * 竣工归档详情
     */
    public static final String NET_GET_COMPLETED_ARCH = "eap/pmsServices/oms/getCompletionPhaseMaterialDetails";
    /**
     * 获取请假详细信息
     */
    public static final String GET_ASKFORLEAVE_DETAIL = "eap/pmsServices/oms/getOmLeaveDetail";
    /**
     * 获取合同详情
     */
    public static final String NET_GET_CONTRACT_LIST = "eap/pmsServices/oms/getContractPages";
    /**
     * 获取工程包列表
     */
    public static final String NET_GET_ENGINEER_PACKAGE_LIST = "eap/pmsServices/oms/getOmProjectPackagePages";
    /**
     * 获取工程包详情
     */
    public static final String NET_GET_ENGINEER_PACKAGE_DETAIL = "eap/pmsServices/oms/getProjectPackageDetail";
    /**
     * 获取任务类型
     */
    public static final String NET_TASK_TYPE = "eap/pmsServices/oms/getAllTaskTypes";
    /**
     * 请假审批
     */
    public static final String ASKFORLEAVE_EXAMINEANDAPPROVE = "eap/pmsServices/oms/updateOmLeave";
    /**
     * 请假审批
     */
    public static final String PLACEONFILE = "eap/pmsServices/oms/finishOmLeave";
    /**
     * 新增请假信息
     */
    public static final String UPDATE_ASKFORLEAVE_INFO = "eap/pmsServices/oms/againSubmit";
    /**
     * 获取报警提醒
     */
    public static final String GET_WARNING_LIST = "eap/pmsServices/oms/getWarningPage";
    /**
     * 获取待办事项
     */
    public static final String GET_TODOS_LIST = "eap/pmsServices/oms/todoList";

    /**
     * 获取最新版本信息
     */
    public static final String LAST_MOBILE_VERSION_URL = "eap/pmsServices/opsAppService/lastMobileVersion";
    /**
     * 修改密码请求路径
     */
    public static final String CHANGE_PASSWORD_URL = "eap/pmsServices/opsAppService/userPsw";
    /**
     * 获取评价列表
     */
    public static final String REQ_EVALUATE_LIST = "eap/pmsServices/oms/getOmConstructionPhaseComment";

    /**
     * 新增评价
     */
    public static final String ADD_EVALUATE_LIST = "eap/pmsServices/oms/addOmConstructionPhaseComment";

    /**
     * 1 删除施工阶段材料图片
     * 2 删除竣工阶段材料图片
     */
    public static final String REMOVE_PIC1 = "eap/pmsServices/oms/deleteOmConstructionPhaseMaterialPic";
    public static final String REMOVE_PIC2 = "eap/pmsServices/oms/deleteOmCompletionPhaseMaterialPic";
    /**
     * 1 修改工程施工阶段材料
     * 2 修改工程竣工阶段材料
     */
    public static final String UPDATE_OMCONSTRUCTIONMATERIAL1 = "eap/pmsServices/oms/updateOmConstructionPhaseMaterial";
    public static final String UPDATE_OMCONSTRUCTIONMATERIAL2 = "eap/pmsServices/oms/updateOmCompletionPhaseMaterial";
    /**
     * 1 上传施工阶段材料图片
     * 2 上传竣工阶段材料图片
     */
    public static final String UPLOAD_OMCONSTRUCTIONMATERIAL_PIC1 = "eap/pmsServices/oms/addOmConstructionPhaseMaterial";
    public static final String UPLOAD_OMCONSTRUCTIONMATERIAL_PIC2 = "eap/pmsServices/oms/addOmCompletionPhaseMaterial";
    /**
     * 1 删除施工阶段材料
     * 2 删除竣工阶段材料
     */
    public static final String DELETE_OMCONSTRUCTIONMATERIAL1 = "eap/pmsServices/oms/deleteOmConstructionPhaseMaterial";
    public static final String DELETE_OMCONSTRUCTIONMATERIAL2 = "eap/pmsServices/oms/deleteOmCompletionPhaseMaterial";

}



