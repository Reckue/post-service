package com.reckue.post.repository.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.reckue.post.exception.ReckueIllegalArgumentException;
import com.reckue.post.model.Node;
import com.reckue.post.model.node.*;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

public class NodeDao {
    ClassModel<Parent> parent = ClassModel.builder(Parent.class).enableDiscriminator(true).build();
    ClassModel<AudioNode> audioModel = ClassModel.builder(AudioNode.class).enableDiscriminator(true).build();
    ClassModel<ListNode> listModel = ClassModel.builder(ListNode.class).enableDiscriminator(true).build();
    ClassModel<CodeNode> codeModel = ClassModel.builder(CodeNode.class).enableDiscriminator(true).build();
    ClassModel<ImageNode> imageModel = ClassModel.builder(ImageNode.class).enableDiscriminator(true).build();
    ClassModel<PollNode> pollModel = ClassModel.builder(PollNode.class).enableDiscriminator(true).build();
    ClassModel<TextNode> textModel = ClassModel.builder(TextNode.class).enableDiscriminator(true).build();
    ClassModel<VideoNode> videoModel = ClassModel.builder(VideoNode.class).enableDiscriminator(true).build();
    ClassModel<Node> nodeModel = ClassModel.builder(Node.class).build();

    CodecRegistry pojoCodecRegistry = fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider
                    .builder()
                    .register(parent,
                            audioModel,
                            listModel,
                            codeModel,
                            imageModel,
                            pollModel,
                            textModel,
                            videoModel,
                            nodeModel)
                    .automatic(true)
                    .build()
            ));

    private final MongoClient client = new MongoClient("localhost",
            MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
    private final MongoDatabase database = client.getDatabase("post-develop");
    private final MongoCollection<Node> collection = database.getCollection("node", Node.class);

    public List<Node> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    public List<Node> findAll(Integer limit, Integer offset, String sort, Boolean desc) {
        return findAllByTypeAndDesc(sort, desc)
                .skip(offset)
                .limit(limit)
                .into(new ArrayList<>());
    }

    /**
     * This method is used to sort objects by type.
     *
     * @param sort type of sorting: type, status, source, createdDate, modificationDate, userId default - id
     * @return list of objects of class Node sorted by the selected parameter for sorting
     */
    public FindIterable<Node> findAllByTypeAndDesc(String sort, boolean desc) {
        List<String> fields = List.of(
        "type", "status", "source", "createdDate",
        "modificationDate", "userId", "id");
        if (fields.contains(sort)) {
            if (desc) {
                return collection.find().sort(descending(sort));
            }
            return collection.find().sort(ascending(sort));
        }
        throw new ReckueIllegalArgumentException("Such field as " + sort + " doesn't exist");
    }

}
