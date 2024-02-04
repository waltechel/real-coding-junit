/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

package com.realcoding.junit.ch01;

public class BadCalculatorTest {

    public static void main(String[] args) {                                    // 실행해야 하는 테스트를 main 메서드에 작성할 수밖에 없다.
        Calculator calculator = new Calculator();
        try {
            double ret1 = calculator.add(50, 10);
            if (ret1 != 60) {
                throw new RuntimeException("calculator add has a problem");     // 오류가 있다는 것만을 알려줄 뿐, 어떤 문제가 있는지는 알려주지 않는다.
            }
            double ret2 = calculator.subtract(50, 10);                               // 앞선 메서드가 실패했을 때 실행되지 않는다(앞 테스트 결과에 영향을 받는다).
            if (ret2 != 40) {
                throw new RuntimeException("calculator subtract has a problem");
            }
            double ret3 = calculator.divide(50, 3);                               // 앞선 메서드가 실패했을 때 실행되지 않는다(앞 테스트 결과에 영향을 받는다).
            if (16.6 - ret3 < 0.1) {
                throw new RuntimeException("calculator divide has a problem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
