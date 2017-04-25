struct inarg{
	int a;
        int b;
};

struct outarg{
	int out;
};

struct String_inarg{
	char a[20];
        char b[20];
};

struct stroutarg{
	char out[20];
};

program RPC{
	version factorial{
		struct outarg addition(struct inarg in)=1;
	}=1;
	version Sub{
		struct outarg sub(struct inarg in)=2;
	}=2;
	version cat{
		struct stroutarg str(struct String_inarg in)=3;
	}=3;
        version fact{
		struct outarg facto(struct inarg in)=4;
	}=4;
}=11111111;
