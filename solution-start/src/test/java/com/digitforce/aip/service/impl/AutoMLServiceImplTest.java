package com.digitforce.aip.service.impl;

import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.aip.test.BaseTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自动调参服务接口类测试类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2023/01/09 23:00
 */
@Slf4j
public class AutoMLServiceImplTest extends BaseTest {
    @Resource
    private AutoMLService autoMLService;

    @Test
    @SneakyThrows
    public void createTask() {
        String automlParams = "{\n" +
                "  \"automl_params\": {\n" +
                "    \"experiment_name\": \"server-test-loss-warning\",\n" +
                "    \"namespace\": \"kubeflow-user-example-com\",\n" +
                "    \"algorithm_spec\": {\n" +
                "      \"algorithm_name\": \"random\"\n" +
                "    },\n" +
                "    \"objective_spec\": {\n" +
                "      \"type\": \"maximize\",\n" +
                "      \"goal\": 0.99,\n" +
                "      \"objective_metric_name\": \"test-auc\",\n" +
                "      \"additional_metric_names\": [\n" +
                "        \"test-logloss\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"parameters\": [\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"5\",\n" +
                "            \"4\",\n" +
                "            \"3\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"max_depth\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"128\",\n" +
                "            \"256\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"n_estimators\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.1\",\n" +
                "            \"0.05\",\n" +
                "            \"0.01\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"learning_rate\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.1\",\n" +
                "            \"1\",\n" +
                "            \"100\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"scale_pos_weight\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"pipeline_id\": \"298f656a-4f58-4398-8db0-4e3bd7436fc7\",\n" +
                "  \"pipeline_params\": {\n" +
                "    \"global_params\": {\n" +
                "      \"sample_select\": {\n" +
                "        \"active_before_days\": 3,\n" +
                "        \"active_after_days\": 5\n" +
                "      },\n" +
                "      \"model\": {\n" +
                "      },\n" +
                "      \"feature_create\": {\n" +
                "        \"active_before_days\": 3,\n" +
                "        \"active_after_days\": 5\n" +
                "      }\n" +
                "    },\n" +
                "    \"flag\": \"AUTOML\"\n" +
                "  }\n" +
                "}";
        String taskId = autoMLService.createTask(automlParams);
        log.info("taskId: {}", taskId);
    }

    @Test
    public void getStatus() {
        AutoMLRunStatusEnum status = autoMLService.getStatus("ae1f8809-b339-450a-a19a-7cd111561304");
        System.out.println(status);
    }

    @Test
    public void getAutoMLResult() {
        List<BestParameter> bestParameters = autoMLService.getAutoMLResult("ae1f8809-b339-450a-a19a-7cd111561304");
        System.out.println(bestParameters);
    }

    @Test
    public void print() {
        String automlParams = "{\n" +
                "  \"automl_params\": {\n" +
                "    \"experiment_name\": \"server-test-loss-warning\",\n" +
                "    \"namespace\": \"kubeflow-user-example-com\",\n" +
                "    \"algorithm_spec\": {\n" +
                "      \"algorithm_name\": \"random\"\n" +
                "    },\n" +
                "    \"objective_spec\": {\n" +
                "      \"type\": \"maximize\",\n" +
                "      \"goal\": 0.99,\n" +
                "      \"objective_metric_name\": \"test-auc\",\n" +
                "      \"additional_metric_names\": [\n" +
                "        \"test-logloss\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"parameters\": [\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"5\",\n" +
                "            \"4\",\n" +
                "            \"3\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"max_depth\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"128\",\n" +
                "            \"256\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"n_estimators\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.1\",\n" +
                "            \"0.05\",\n" +
                "            \"0.01\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"learning_rate\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.1\",\n" +
                "            \"1\",\n" +
                "            \"100\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"scale_pos_weight\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"pipeline_id\": \"298f656a-4f58-4398-8db0-4e3bd7436fc7\",\n" +
                "  \"pipeline_params\": {\n" +
                "    \"global_params\": {\n" +
                "      \"sample_select\": {\n" +
                "        \"active_before_days\": 3,\n" +
                "        \"active_after_days\": 5\n" +
                "      },\n" +
                "      \"model\": {\n" +
                "      },\n" +
                "      \"feature_create\": {\n" +
                "        \"active_before_days\": 3,\n" +
                "        \"active_after_days\": 5\n" +
                "      }\n" +
                "    },\n" +
                "    \"flag\": \"AUTOML\"\n" +
                "  }\n" +
                "}";
        System.out.println(automlParams);
    }
}