
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` varchar(255) NOT NULL,
  `method` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES ('50b9ec84-8909-4538-9fc2-704f599eff64','GET','列表查询','/pet/cats'),('82912f3a-ca42-48d0-90f9-d7ef82f62c91','PUT','删除','/pet/cats/*'),('8646d80b-7214-49ce-a8a3-64fd20658b10','PUT','修改','/pet/cats'),('bd072632-9875-41dd-8289-263c969d6d77','GET','查询详情','/pet/cats/*'),('d2f883eb-b2fa-43fa-85fd-f0e45728e105','POST',' 新增','/pet/cats');
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bqy406dtsr7j7d6fawi1ckyn1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('55246b21-81ca-43ae-bd6e-b90c3d99db2b','系统管理员','ADMIN'),('9735e2c7-024f-4c0f-ae5e-78a037366693','普通用户',' USER');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_resource` (
  `role_id` varchar(255) NOT NULL,
  `resource_id` varchar(255) NOT NULL,
  KEY `FKkj7e3cva1e2s3nsd0yghpbsnk` (`resource_id`),
  KEY `FK7urjh5xeujvp29nihwbs5b9kr` (`role_id`),
  CONSTRAINT `FK7urjh5xeujvp29nihwbs5b9kr` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FKkj7e3cva1e2s3nsd0yghpbsnk` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resource`
--

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` VALUES ('55246b21-81ca-43ae-bd6e-b90c3d99db2b','50b9ec84-8909-4538-9fc2-704f599eff64'),('55246b21-81ca-43ae-bd6e-b90c3d99db2b','82912f3a-ca42-48d0-90f9-d7ef82f62c91'),('55246b21-81ca-43ae-bd6e-b90c3d99db2b','8646d80b-7214-49ce-a8a3-64fd20658b10'),('55246b21-81ca-43ae-bd6e-b90c3d99db2b','bd072632-9875-41dd-8289-263c969d6d77'),('55246b21-81ca-43ae-bd6e-b90c3d99db2b','d2f883eb-b2fa-43fa-85fd-f0e45728e105'),('9735e2c7-024f-4c0f-ae5e-78a037366693','50b9ec84-8909-4538-9fc2-704f599eff64'),('9735e2c7-024f-4c0f-ae5e-78a037366693','bd072632-9875-41dd-8289-263c969d6d77');
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_user`
--

DROP TABLE IF EXISTS `sys_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_user` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  KEY `FKe853e1gbgbb6ov7al35mfd3nm` (`role_id`),
  KEY `FK1qf7bu5lhwt9y00uesmcgnbxd` (`user_id`),
  CONSTRAINT `FK1qf7bu5lhwt9y00uesmcgnbxd` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKe853e1gbgbb6ov7al35mfd3nm` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_user`
--

LOCK TABLES `sys_role_user` WRITE;
/*!40000 ALTER TABLE `sys_role_user` DISABLE KEYS */;
INSERT INTO `sys_role_user` VALUES ('245b3120-08b3-4957-88f2-d4f4eda8a5bd','55246b21-81ca-43ae-bd6e-b90c3d99db2b'),('245b3120-08b3-4957-88f2-d4f4eda8a5bd','9735e2c7-024f-4c0f-ae5e-78a037366693'),('9e2a3e87-b882-4ef4-b27f-017e4fee4535','9735e2c7-024f-4c0f-ae5e-78a037366693');
/*!40000 ALTER TABLE `sys_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '1',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('245b3120-08b3-4957-88f2-d4f4eda8a5bd',1,'$2a$10$P6ayJ.TMOy4v6bV4f3vJ0eynaDDA/Le49SAaxunb/OOcMNCzlSfHW','Tom'),('9e2a3e87-b882-4ef4-b27f-017e4fee4535',1,'$2a$10$GLUBcNoJh5SmjpxPlVqXmOBbA.xTop7RkNSsAPj3Ss0rKn7BkfNF.','Lucy');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-24 18:17:12
