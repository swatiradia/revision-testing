package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 2;
//    Only runs when test fails
    @Override
    public boolean retry(ITestResult result) {
        if (count<maxTry){
            count++;
            return true;
        }
        return false;
    }
}
