/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DrollTest
 * Author:   wangjiang
 * Date:     2019/11/5 12:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cn.drool.test;

import com.cn.drool.domain.Customer;
import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.definition.KiePackage;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author wangjiang
 * @create 2019/11/5
 * @since 1.0.0
 */
public class DrollTest {

    public static void main(String[] args) {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("rules/rule.drl",DrollTest.class), ResourceType.DRL);
        Collection<KiePackage> packages = builder.getKnowledgePackages();
        KieBaseConfiguration kbConfig = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
        kbConfig.setProperty("org.drools.sequential","true");
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(kbConfig);
        knowledgeBase.addPackages(packages);
        KieSession session = knowledgeBase.newKieSession();
        Customer customer = new Customer();
        customer.setName("zhangsan");
        customer.setAge(30);
        session.insert(customer);
        session.fireAllRules();
        session.dispose();
    }

}
