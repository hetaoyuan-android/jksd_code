package com.glens.jksd.utils;

public class RepairConstantUtils {

    static final public int NET_ERROR = 2000; // 网络错误

    public static final String REPAIR_RECORD_LAYOUT = "recordType";
    public static final String REPAIR_RECORD_ONE = "recordOne";
    public static final String REPAIR_RECORD_TWO = "recordTwo";
    public static final String REPAIR_RECORD_THREE = "recordThree";
    public static final int REPAIR_RECORD_ITEM_ONE = 1;
    public static final int REPAIR_RECORD_ITEM_TWO = 2;
    public static final int REPAIR_RECORD_ITEM_THREE = 3;

    public static final String REPAIR_LOGIN_KEY = "userName";
    public static final String USER_SAVE_NAME = "saveName";
    public static final String USER_SAVE_CODE = "userCode";
    public static final String USER_SAVE_KEY = "savePd";
    public static final String REPAIR_CHECK_STATE = "checkState";

    //检测管理的三个必填参数 taskCode
    public static final String INFRARED_TASK_CODE = "taskCode";


    public static final int DETECTION_GROUND_RESISTANCE = 1;  // 接地电阻
    public static final int DETECTION_INFRARDE = 2;   // 红外测温
    public static final int DETECTION_PAY_ACROSS_MEASURE = 3;  // 交跨测量

    // 接地电阻 已完成和未完成
    public static final int GROUND_DONE = 1;
    public static final int GROUND_NO_DISPOSE = 0;

    // 交跨测量 四种交跨方式
    public static final int MEASURE_ROAD = 1;
    public static final int MEASURE_RAILWAY = 2;
    public static final int MEASURE_IMP_CHANNEL = 3;
    public static final int MEASURE_RIVER = 4;

    // 红外测温两种处理状态
    public static final int INFRARED_DONE = 1;
    public static final int INFRARED_NO_DISPOSE = 0;

    public static final String REPAIR_TECHNICAL = "技改";
    public static final String REPAIR_FIXED = "大修";
    public static final String REPAIR_DAILY = "日常维护";

    public static final int DETECTION_CREATE = 1;
    public static final int DETECTION_DOING = 2;
    public static final int DETECTION_DONE = 3;

    public static final int REPAIR_CREATE = 1;//创建
    public static final int REPAIR_DOING = 2;//执行中
    public static final int REPAIR_DONE = 3;//已完成
    public static final int REPAIR_DOWN = 3;
    public static final int REPAIR_END = 4;

    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String TASKCODE = "recodeCode";
    public static final String DATUM_CODE = "datumCode";
    public static final String SELECT_TYPE = "selectType";
    public static final String LINE_ID = "lineId";
    public static final String LINE_LIST = "lineList";
    public static final String TOWER_LIST = "towerList";
    public static final String LINE_SINGLE = "lineSingle";
    public static final String TOWER_SINGLE = "towerSingle";
    public static final String TASKTYPE = "taskType";
    public static final String LINENAME = "lineName";
    public static final String LINENUMBER = "lineNumber";
    public static final String POWERLEVEL = "powerLevel";
    public static final String LINEBEAN = "lineBean";
    public static final String TOWERBEAN = "towerBean";

    public static final String DATA_LOADING = "数据加载中";
    public static final String UP_PIC = "照片";
    public static final String UP_AUDIO = "音频";
    public static final int DIALOG_TIME = 1000;

    public static final int DETECTION_PHOTO_CODE = 1001;
    public static final int SELECT_EQUIPMENT = 1002;
    public static final int SELECT_EQUIPMENT_RESULT = 1003;
    public static final int SELECT_TOWER = 1004;

    public static final int START_TOWER = 2010;
    public static final int END_TOWER = 2011;

    //图片添加删除
    public static final int DELETE_IMAGE_SUCCESS = 2001;
    public static final int POP_POPUPWINDOW_SHOW = 2002;
    public static final int DELETE_IMAGE = 2003;
    public static final int NET_POST_DETECTION_IMAGE = 2004;

    public static final int NET_OMCONSTRUCTION_PHASEMATERIAL_SUCCESS = 2005;
    public static final int UPDATE_OMCONSTRUCTIONMATERIAL_SUCCESS = 2006;
    public static final int NET_OMCOMPLETION_PHASEMATERIAL_SUCCESS = 2007;

    public static final int REPAIR_ONE = 1;
    public static final int REPAIR_TWO = 2;
    public static final int REPAIR_THREE = 3;
    public static final int REPAIR_FOUR = 4;
    public static final int REPAIR_FIVE = 5;
    public static final int REPAIR_SIX = 6;

//    1:领用 2:检查 3:挂接(装设) 4:拆除 5:归还
    public static final String TASK_GROUND_ACCEPT = "领用";
    public static final String TASK_GROUND_CHECK = "检查";
    public static final String TASK_GROUND_HUNG = "挂接";
    public static final String TASK_GROUND_BROKEN = "拆除";
    public static final String TASK_GROUND_RETURN = "归还";

    public static final int START_WORK = 1;
    public static final int DOWN_WORK = 2;

    public static final String WORK_RECORD_TYPE = "开收工录音";
    public static final String INFORMATION_TYPE = "检修资料录音";

    public static final String START_WORK_TEXT = "开工";
    public static final String DOWN_WORK_TEXT = "收工";

    public static final String TASK_REPAIR_UNDO = "0";
    public static final String TASK_REPAIR_DOWN = "1";

    public static final String TASK_REPAIR_UNDO_TEXT = "未检修";
    public static final String TASK_REPAIR_DOWN_TEXT = "已检修";

    public static final String CLEAN_BEFORE = "清扫前";
    public static final String CLEAN_AFTER = "清扫后";

    public static final String PHASEA = "A";
    public static final String PHASEB = "B";
    public static final String PHASEC = "C";

    public static final String POWER_ZERO = "0";
    public static final String POWER_ONE = "1";
    public static final String POWER_TWO = "2";


    //0: 不去重(走线检查 、现场勘查) 1: 登杆检查 2: 绝缘子清扫 3: RTV喷涂 4:憎水性试验
    public static final int LINE_BASE = 0;
    public static final int LINE_TOWER = 1;
    public static final int LINE_INSULATOR = 2;
    public static final int LINE_SPRAY = 3;
    public static final int LINE_WATER = 4;

    public static final int SPRAY_BEFORE = 3;
    public static final int SPRAY_ONE = 1;
    public static final int SPRAY_TWO = 2;

    //资料类型 1：交底记录 2：施工三措 3：工作票 4：其他 int
    public static final int INFORMATION_RECORD = 1;
    public static final int INFORMATION_METHOD = 2;
    public static final int INFORMATION_WORK = 3;
    public static final int INFORMATION_OTHERS = 4;
}
