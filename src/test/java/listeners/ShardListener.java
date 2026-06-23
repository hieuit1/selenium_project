package listeners;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.ArrayList;
import java.util.List;

public class ShardListener implements IMethodInterceptor {

    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
        // Đọc tham số từ GitHub Actions truyền vào qua Maven
        String shardIndexStr = System.getProperty("shardIndex");
        String shardTotalStr = System.getProperty("shardTotal");

        // RẤT QUAN TRỌNG: Nếu bạn chạy test ở máy Local (không có tham số CI),
        // thì bỏ qua sharding và chạy TOÀN BỘ test bình thường.
        if (shardIndexStr == null || shardTotalStr == null) {
            return methods;
        }

        int shardIndex = Integer.parseInt(shardIndexStr);
        int shardTotal = Integer.parseInt(shardTotalStr);
        List<IMethodInstance> methodsToRun = new ArrayList<>();

        // Thuật toán Round-Robin: Chia đều test case một cách công bằng nhất
        // Ví dụ: 10 test case, 4 máy ảo
        // Máy 1 chạy test: 0, 4, 8
        // Máy 2 chạy test: 1, 5, 9
        // Máy 3 chạy test: 2, 6
        // Máy 4 chạy test: 3, 7
        for (int i = 0; i < methods.size(); i++) {
            if (i % shardTotal == (shardIndex - 1)) {
                methodsToRun.add(methods.get(i));
            }
        }

        System.out.println("=================================================");
        System.out.println("🚀 [SHARDING INFO] MÁY ẢO " + shardIndex + "/" + shardTotal);
        System.out
                .println("🚀 [SHARDING INFO] ĐÃ NHẬN " + methodsToRun.size() + " / " + methods.size() + " TEST CASES");
        System.out.println("=================================================");

        return methodsToRun;
    }
}