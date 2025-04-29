package com.example.demo.entity;

public enum ProgressStatus {
	NOT_STARTED(0, "未着手"), IN_PROGRESS(1, "進行中"), COMPLETED(2, "完了");

	private final int code;
	private final String label;

	ProgressStatus(int code, String label) {
		this.code = code;
		this.label = label;
	}

	public int getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	// 数値からEnumを取得するメソッド
	public static ProgressStatus fromCode(int code) {
		for (ProgressStatus status : values()) {
			if (status.code == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid progress code: " + code);
	}
}
