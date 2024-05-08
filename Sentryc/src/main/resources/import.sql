-- marketplaces
INSERT INTO MARKETPLACES("id", "description") values('amazon.eu', 'Amazon in European Union.');
INSERT INTO MARKETPLACES("id", "description") values('flipkart.com', 'Flipkart India.');
INSERT INTO MARKETPLACES("id", "description") values('amazon.in', 'Amazon in India.');
INSERT INTO MARKETPLACES("id", "description") values('myntra.com', 'Myntra');
INSERT INTO MARKETPLACES("id", "description") values('meeso.com', 'Sabse sasta!');

-- seller_infos
INSERT INTO SELLER_INFOS("id", "name", "url", "country", "external_id", "marketplace_id") VALUES('583d7411-415a-4c58-8ccb-826baacebc3f', 'Ayushya', 'http://ayushyalife.in', 'INDIA', 'random_id1', 'flipkart.com');
INSERT INTO SELLER_INFOS("id", "name", "url", "country", "external_id", "marketplace_id") VALUES('efa1c498-228c-41cd-b29c-ce004ae39379', 'Raghav', 'http://raghavlife.in', 'INDIA','random_id2', 'myntra.com');
INSERT INTO SELLER_INFOS("id", "name", "url", "country", "external_id", "marketplace_id") VALUES('3cfe9699-9f74-456a-b9f6-b2a66ddba214', 'Madhav', 'http://madhavlife.in', 'INDIA','random_id3', 'amazon.in');
INSERT INTO SELLER_INFOS("id", "name", "url", "country", "external_id", "marketplace_id") VALUES('d55c4a50-3d10-4ce9-8b41-a740e6babdd2', 'Shiva', 'http://shivalife.in', 'INDIA','random_id4', 'meeso.com');
INSERT INTO SELLER_INFOS("id", "name", "url", "country", "external_id", "marketplace_id") VALUES('ab281550-fb2e-439f-927e-9c77fe675b3b', 'Krishna', 'http://krishnalife.in', 'INDIA','random_id5', 'amazon.eu');

-- producers
INSERT INTO PRODUCERS("id", "name", "created_at") VALUES('4b1d3262-d939-4c1c-9443-ee0cee7f3644', 'Nike', NOW());
INSERT INTO PRODUCERS("id", "name", "created_at") VALUES('98ab9885-fb53-4153-8520-1a6f4330da48', 'Adidas', NOW());
INSERT INTO PRODUCERS("id", "name", "created_at") VALUES('3520e862-d58b-42c8-baa5-201c91770d4b', 'Sketchers', NOW());
INSERT INTO PRODUCERS("id", "name", "created_at") VALUES('98fe01a3-38ad-4abe-a866-dfcc9a7181b3', 'Action', NOW());
INSERT INTO PRODUCERS("id", "name", "created_at") VALUES('272644b2-0a33-4729-a50c-3844628c7c3d', 'Khadims', NOW());

-- sellers
INSERT INTO SELLERS("id", "producer_id", "seller_info_id", "state") VALUES('719c31b2-3165-4794-9912-b83faacd859e','98ab9885-fb53-4153-8520-1a6f4330da48', 'efa1c498-228c-41cd-b29c-ce004ae39379', 'REGULAR');
INSERT INTO SELLERS("id", "producer_id", "seller_info_id", "state") VALUES('e5288949-06dd-428f-a2cd-afc64c88ee35','4b1d3262-d939-4c1c-9443-ee0cee7f3644', '583d7411-415a-4c58-8ccb-826baacebc3f', 'WHITELISTED');
INSERT INTO SELLERS("id", "producer_id", "seller_info_id", "state") VALUES('2ed81951-c88b-43b7-9604-ccacb26c78bd','3520e862-d58b-42c8-baa5-201c91770d4b', '3cfe9699-9f74-456a-b9f6-b2a66ddba214', 'GREYLISTED');
INSERT INTO SELLERS("id", "producer_id", "seller_info_id", "state") VALUES('7d1fe8b4-34c3-4fd3-9e21-0850213d1a2b','272644b2-0a33-4729-a50c-3844628c7c3d', 'd55c4a50-3d10-4ce9-8b41-a740e6babdd2', 'REGULAR');
INSERT INTO SELLERS("id", "producer_id", "seller_info_id", "state") VALUES('0b1b1e12-8784-452d-a8f8-15ddcd601828','98fe01a3-38ad-4abe-a866-dfcc9a7181b3', 'ab281550-fb2e-439f-927e-9c77fe675b3b', 'BLACKLISTED');
