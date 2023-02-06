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
                "    \"experiment_name\": \"server-test-lookalike\",\n" +
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
                "            \"128\",\n" +
                "            \"256\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"batch_size\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.2\",\n" +
                "            \"0.3\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"dnn_dropout\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"feasible_space\": {\n" +
                "          \"list\": [\n" +
                "            \"0.1\",\n" +
                "            \"0.01\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"name\": \"lr\",\n" +
                "        \"parameter_type\": \"categorical\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"pipeline_id\": \"e0e53e2b-4f71-42f1-a675-5eb1f39a1852\",\n" +
                "  \"pipeline_params\": {\n" +
                "    \"global_params\": {\n" +
                "      \"sample_select\":\n" +
                "      {},\n" +
                "      \"raw_user_feature\":\n" +
                "      {\n" +
                "          \"raw_user_feature_table_name\": \"algorithm.tmp_raw_user_feature_table_name\"\n" +
                "      },\n" +
                "      \"raw_item_feature\":\n" +
                "      {\n" +
                "          \"raw_item_feature_table_name\": \"algorithm.tmp_raw_item_feature_table_name\"\n" +
                "      },\n" +
                "      \"zq_feature_calculator\":\n" +
                "      {\n" +
                "          \"raw_user_feature_table_name\": \"algorithm.tmp_raw_user_feature_table_name\",\n" +
                "          \"raw_item_feature_table_name\": \"algorithm.tmp_raw_item_feature_table_name\"\n" +
                "      },\n" +
                "      \"raw_sample2model_sample\":\n" +
                "      {\n" +
                "          \"model_sample_table_name\": \"algorithm.tmp_aip_model_sample\"\n" +
                "      },\n" +
                "      \"model_item_feature\":\n" +
                "      {\n" +
                "          \"model_item_feature_table_name\": \"algorithm.tmp_model_item_feature_table_name\"\n" +
                "      },\n" +
                "      \"model_user_feature\":\n" +
                "      {\n" +
                "          \"model_user_feature_table_name\": \"algorithm.tmp_model_user_feature_table_name\"\n" +
                "      },\n" +
                "      \"feature_create\":\n" +
                "      {},\n" +
                "      \"model\":\n" +
                "      {\n" +
                "          \"model_user_feature_table_name\": \"algorithm.tmp_model_user_feature_table_name\",\n" +
                "          \"user_vec_table_name\": \"algorithm.tmp_user_vec_table_name\"\n" +
                "      },\n" +
                "      \"model_predict\":\n" +
                "      {\n" +
                "          \"output_file_name\": \"result.csv\",\n" +
                "          \"user_vec_table_name\": \"algorithm.aip_zq_lookalike_user_vec\"\n" +
                "      }\n" +
                "  },\n" +
                "    \"flag\": \"AUTOML\"\n" +
                "  }\n" +
                "}";
        System.out.println(automlParams);
        String taskId = autoMLService.createTask(automlParams);
        log.info("taskId: {}", taskId);
    }

    @Test
    public void getStatus() {
        AutoMLRunStatusEnum status = autoMLService.getStatus("bbf0b852-6136-4e41-8a49-367a7669324e");
        System.out.println(status);
    }

    @Test
    public void getAutoMLResult() {
        List<BestParameter> bestParameters = autoMLService.getAutoMLResult("bbf0b852-6136-4e41-8a49-367a7669324e");
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