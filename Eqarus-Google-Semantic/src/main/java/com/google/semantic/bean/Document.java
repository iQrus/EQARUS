package com.google.semantic.bean;

import java.util.List;

import com.google.protobuf.Message;

public final class Document extends com.google.protobuf.GeneratedMessageV3
 {

	public List<Document>documentList;
	
	private String type;
	 private String content;
	
	@Override
	public com.google.protobuf.Message.Builder newBuilderForType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public com.google.protobuf.Message.Builder toBuilder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message getDefaultInstanceForType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected FieldAccessorTable internalGetFieldAccessorTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected com.google.protobuf.Message.Builder newBuilderForType(BuilderParent parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
