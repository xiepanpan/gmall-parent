# gmall-parent

使用mybatisgenerator 生成mapper  dao

**GlobalExceptionHandler  统一处理全部异常  json封装返回前端**

dubbo zookeeper  

mybaitplus

sharding-jdbc配置多数据源 读写分离

elk搭建日志系统

admin-web：排除数据源配置 



redis配置序列化缓存级联菜单 mybatis collection 递归查询级联菜单



商品属性分类查询

-  阿里云文件存储实现图片上传功能

es 商品上下架  商品信息存到es中进行搜索

product->sku->EsProductAttributeValue

**商品上架调用服务时候新增功能 使用failfast模式 失败不重试**
**默认dubbo模式为failover  失败重试**
**使用方法在类上加@Service(cluster="failfast")**

http://dubbo.apache.org/zh-cn/docs/user/references/xml/dubbo-service.html


![](https://xiepanpan123.oss-cn-beijing.aliyuncs.com/%E5%95%86%E5%93%81es%E6%A8%A1%E5%9E%8B.png)



- es mapping修改 **嵌入式对象的Mapping一定要用nested声明，这样才能正确的检索到数据**



分布式锁

异步缓存

微博社交网站登录 

单点登录


### 购物车功能

CompletableFuture 组合式异步编程 查询sku信息和商品信息



![](https://xiepanpan123.oss-cn-beijing.aliyuncs.com/%E5%9C%A8%E7%BA%BF%E8%B4%AD%E7%89%A9%E8%BD%A6%E7%BC%93%E5%AD%98%E6%A8%A1%E5%BC%8F.png)





![](https://xiepanpan123.oss-cn-beijing.aliyuncs.com/%E4%B8%B4%E6%97%B6%E8%B4%AD%E7%89%A9%E8%BD%A6%E7%BC%93%E5%AD%98%E6%A8%A1%E5%9E%8B.png)



rabbitmq 延时队列 死信

**超时订单放入队列中 依次关闭**





还差个

- [ ]  订单功能
- [ ]  支付宝支付

不想搞了。。。












商品数据的Mapping：

```json
GET product/_mapping

DELETE product

PUT /product
{
  "mappings": {
    "info": {
      "properties": {
        "attrValueList": {
		  "type":"nested",
          "properties": {
            "id": {
              "type": "long"
            },
            "name": {
              "type": "keyword"
            },
            "productAttributeId": {
              "type": "long"
            },
            "productId": {
              "type": "long"
            },
            "type": {
              "type": "long"
            },
            "value": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            }
          }
        },
        "brandId": {
          "type": "long"
        },
        "brandName": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "id": {
          "type": "long"
        },
        "keywords": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "name": {
          "type": "text",
		  "analyzer": "ik_max_word",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "newStatus": {
          "type": "long"
        },
        "pic": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "price": {
          "type": "float"
        },
        "productCategoryId": {
          "type": "long"
        },
        "productCategoryName": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "productSn": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          }
        },
        "promotionType": {
          "type": "long"
        },
        "recommandStatus": {
          "type": "long"
        },
        "sale": {
          "type": "long"
        },
        "skuProductInfos": {
		  "type":"nested",
          "properties": {
            "attributeValues": {
			  "type":"nested",
              "properties": {
                "name": {
                  "type": "text",
                  "fields": {
                    "keyword": {
                      "type": "keyword",
                      "ignore_above": 256
                    }
                  }
                },
                "productAttributeId": {
                  "type": "long"
                },
                "productId": {
                  "type": "long"
                },
                "type": {
                  "type": "long"
                },
                "value": {
                  "type": "text",
                  "fields": {
                    "keyword": {
                      "type": "keyword",
                      "ignore_above": 256
                    }
                  }
                }
              }
            },
            "id": {
              "type": "long"
            },
            "lockStock": {
              "type": "long"
            },
            "lowStock": {
              "type": "long"
            },
            "pic": {
              "type": "keyword"
            },
            "price": {
              "type": "float"
            },
            "productId": {
              "type": "long"
            },
            "skuCode": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            },
            "skuTitle": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              },
			      "analyzer": "ik_max_word"
            },
            "sp1": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            },
            "sp2": {
              "type": "text",
              "fields": {
                "keyword": {
                  "type": "keyword",
                  "ignore_above": 256
                }
              }
            },
            "stock": {
              "type": "long"
            }
          }
        },
        "sort": {
          "type": "long"
        },
        "stock": {
          "type": "long"
        },
        "subTitle": {
          "type": "text",
          "fields": {
            "keyword": {
              "type": "keyword",
              "ignore_above": 256
            }
          },
		    "analyzer": "ik_max_word"
        }
      }
    }
  }
}
```

## 第八天 

前端环境搭建  搜索服务 dsl语句

构建dsl语句的大体步骤：

1. 查询
   - 检索
   - 过滤
     - 按属性 品牌 分类 过滤
2. 高亮
3. 聚合
4. 分页

商品的检索sql

```json
GET product/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "nested": {
            "path": "skuProductInfos",
            "query": {
              "match": {
                "skuProductInfos.skuTitle": "手机"
              }
            }
          }
        }
      ],
      "filter": [
        {
          "nested": {
            "path": "attrValueList",
            "query": {
              "bool": {
                "must": [
                  {
                    "match": {
                      "attrValueList.name": "屏幕尺寸"
                    }
                  },
                  {
                    "match": {
                      "attrValueList.value.keyword": "4.7"
                    }
                  }
                ]
              }
            }
          }
        },
        {
          "nested": {
            "path": "attrValueList",
            "query": {
              "bool": {
                "must": [
                  {
                    "match": {
                      "attrValueList.name": "网络"
                    }
                  },
                  {
                    "match": {
                      "attrValueList.value.keyword": "4G"
                    }
                  }
                ]
              }
            }
          }
        },
        {
          "term": {
            "brandName.keyword": "苹果"
          }
        },
        {
          "term": {
            "productCategoryId": "19"
          }
        },
        {
          "range": {
            "price": {
              "gte": 5000,
              "lte": 10000
            }
          }
        }
      ]
    }
  },
  "aggs": {
    "brand_agg": {
      "terms": {
        "field": "brandName.keyword"
      },
      "aggs": {
        "brandId": {
          "terms": {
            "field": "brandId",
            "size": 10
          }
        }
      }
    },
    "category_agg": {
      "terms": {
        "field": "productCategoryName.keyword",
        "size": 10
      }
    },
    "attr_agg": {
      "nested": {
        "path": "attrValueList"
      },
      "aggs": {
        "attrName_agg": {
          "terms": {
            "field": "attrValueList.name",
            "size": 10
          },
          "aggs": {
            "attrValue_agg": {
              "terms": {
                "field": "attrValueList.value.keyword",
                "size": 10
              }
            }
          }
        }
      }
    }
  },
  "highlight": {
    "pre_tags": "<b style='color:red'>",
    "post_tags": "</b>",
    "fields": {
      "skuProductInfos.skuTitle": {}
    }
  },
  "from": 0,
  "size": 12,
  "sort": [
    {
      "price": {
        "order": "asc"
      }
    },
    {
      "sort":{
        "order": "asc"
      }
    }
  ]
}
```

es最重要的就是数据模型

锁测试

